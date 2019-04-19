package com.mod.loan.itf.moxie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.MoxieMobileMessage;
import com.mod.loan.common.message.MoxieZfbMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.config.redis.RedisConst;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.MoxieMobileMapper;
import com.mod.loan.mapper.MoxieZfbFailMapper;
import com.mod.loan.model.MoxieMobile;
import com.mod.loan.model.MoxieZfb;
import com.mod.loan.model.MoxieZfbFail;
import com.mod.loan.model.UserIdent;
import com.mod.loan.service.UserService;
import com.mod.loan.util.MoxieOssUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(value = "moxie")
public class MoxieController {

	public static final Logger logger = LoggerFactory.getLogger(MoxieController.class);
	@Autowired
	UserService userService;
	@Value("${moxie.secret:}")
	String moxieSecret;
	@Autowired
	MoxieMobileMapper moxieMobileMapper;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	MoxieZfbFailMapper moxieZfbFailMapper;
	@Autowired
	RedisMapper redisMapper;

	@RequestMapping(value = "zfb_callback")
	public void zfb_callback(@RequestHeader("X-Moxie-Event") String event,
			@RequestHeader("X-Moxie-Signature") String sign, @RequestBody String requestBody,
			HttpServletResponse response) {
		if (!sign.equals(base64Hmac256(requestBody, moxieSecret))) {
			logger.error("签名错误,sgin={}，返回参数={}", sign, requestBody);
			return;
		}
		JSONObject body = JSON.parseObject(requestBody);
		String task_id = body.getString("task_id");
		if (!redisMapper.lock(RedisConst.MOXIE_ZFB_LOCK + task_id, 2)) {
			return;
		}
		String message = body.getString("message");
		Long uid = body.getLong("user_id");
		if (uid==null) {
			logger.error("uid异常,返回参数={}", requestBody);
			return;
		}
		MoxieZfb moxieZfb = new MoxieZfb();
		moxieZfb.setUid(uid);
		moxieZfb.setTaskId(task_id);
		moxieZfb.setStatus(event);
		moxieZfb.setCreateTime(new Date());
		boolean result = body.getBooleanValue("result");
		
		// 拦截失败的认证
		if("task.fail".equals(event) || !result) {
			// 同一个task_id的失败通知只处理一次
			if (moxieZfbFailMapper.selectByTaskId(task_id) != null) {
				logger.error("魔蝎支付宝task通知顺序异常，task_id={}", task_id);
				response.setStatus(201);
				return;
			} else {
				if (!message.contains("账户名或登录密码不正确")) {
					MoxieZfbFail moxieZfbFail = new MoxieZfbFail();
					moxieZfbFail.setTaskId(moxieZfb.getTaskId());
					moxieZfbFail.setUid(moxieZfb.getUid());
					moxieZfbFail.setStatus(moxieZfb.getStatus());
					moxieZfbFail.setRemark(message);
					moxieZfbFail.setCreateTime(new Date());
					moxieZfbFailMapper.insertSelective(moxieZfbFail);
				}
			}
		}
		
		UserIdent ident = null;
		switch (event) {
		case "task":
			// 拦截通知顺序错误的登陆成功
			if (result && moxieZfbFailMapper.selectByTaskId(task_id) != null) {
				logger.error("魔蝎支付宝task登陆成功通知顺序异常，task_id={}", task_id);
				break;
			}
			ident = new UserIdent();
			ident.setUid(uid);
			ident.setAlipay(result ? 1 : 3);
			userService.updateUserZfb(ident, moxieZfb);
			break;
		case "task.fail":// 魔蝎采集数据失败（原始数据）
			ident = new UserIdent();
			ident.setUid(uid);
			ident.setAlipay(3);
			userService.updateUserZfb(ident, moxieZfb);
			break;
		case "report":// 魔蝎报告数据生成
			ident = new UserIdent();
			ident.setUid(uid);
			if (result) {
				ident.setAlipay(2);
			} else {
				ident.setAlipay(3);
			}
			userService.updateUserZfb(ident, moxieZfb);
			if(result){
				MoxieZfbMessage moxieZfbMessage = new MoxieZfbMessage();
				moxieZfbMessage.setId(moxieZfb.getId());
				moxieZfbMessage.setTaskId(task_id);
				rabbitTemplate.convertAndSend(RabbitConst.queue_moxie_zfb, moxieZfbMessage);
			}
			break;
		default: // task.submit和bill两种事件
			break;
		}
		response.setStatus(201);
	}

	@RequestMapping(value = "mobile_callback")
	public void mobile_callback(@RequestHeader("X-Moxie-Event") String event,
			@RequestHeader("X-Moxie-Signature") String sign, @RequestBody String requestBody,
			HttpServletResponse response) {
		if (!sign.equals(base64Hmac256(requestBody, moxieSecret))) {
			logger.info("签名错误，返回参数==" + requestBody);
			return;
		}
		JSONObject body = JSON.parseObject(requestBody);
		String task_id = body.getString("task_id");
		if (!redisMapper.lock(RedisConst.MOXIE_MOBILE_LOCK + task_id, 2)) {
			return;
		}
		Long uid = body.getLong("user_id");
		String phone = body.getString("mobile");
		if (uid==null) {
			logger.error("uid异常,返回参数={}", requestBody);
			return;
		}
		boolean result = body.getBooleanValue("result");
		UserIdent ident = null;
		switch (event) {
		case "task":// 登录结果，认证中
			ident = new UserIdent();
			ident.setUid(uid);
			ident.setMobile(result ? 1 : 3);
			userService.updateUserMobile(ident, null);
			break;
		case "task.fail":// 魔蝎采集数据失败（原始数据）
			ident = new UserIdent();
			ident.setUid(uid);
			ident.setMobile(3);
			userService.updateUserMobile(ident, null);
			break;
		case "report":// 魔蝎报告数据生成
			ident = new UserIdent();
			ident.setUid(uid);
			MoxieMobile moxieMobile = null;
			if (result) {
				ident.setMobile(2);
				moxieMobile = new MoxieMobile();
				moxieMobile.setUid(uid);
				moxieMobile.setTaskId(task_id);
				moxieMobile.setStatus(event);
				moxieMobile.setCreateTime(new Date());
				moxieMobile.setPhone(phone);
			} else {
				ident.setMobile(3);
			}
			userService.updateUserMobile(ident, moxieMobile);
			if(result){
				MoxieMobileMessage message = new MoxieMobileMessage();
				message.setTaskId(task_id);
				message.setId(moxieMobile.getId());
				rabbitTemplate.convertAndSend(RabbitConst.queue_moxie_mobile, moxieMobile);
			}
			break;
		default: // task.submit和bill两种事件
			break;
		}
		response.setStatus(201);
	}

	@RequestMapping(value = "magic_wand_report")
	public String magic_wand_report(String taskId) {
		String result = "";
		if (StringUtils.isEmpty(taskId)) {
			return result;
		}
		try {
			MoxieMobile moxieMobile = moxieMobileMapper.selectOneByTaskId(taskId);
			if (moxieMobile == null) {
				return result;
			}
			if (0 == moxieMobile.getTagMagic()) {
				Boolean upload = MoxieOssUtil.uploadMagicWandReport(taskId);
				if (!upload) {
					logger.error("魔杖数据上传失败，taskId={}", taskId);
					return result;
				} else {
					moxieMobileMapper.updateMagicTag(taskId);
				}
			}

			result = MoxieOssUtil.magicWandReport(taskId);
		} catch (Exception e) {
			logger.error("taskId={}", taskId);
			logger.error("获取魔杖数据异常", e);
		}
		return result;
	}

	private static String base64Hmac256(String payload, String secret) {
		try {
			Mac sha256Hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			sha256Hmac.init(secretKey);
			return Base64.encodeBase64String(sha256Hmac.doFinal(payload.getBytes()));
		} catch (Exception ignored) {
			return "";
		}
	}
}
