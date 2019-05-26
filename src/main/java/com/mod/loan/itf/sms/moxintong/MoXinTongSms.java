package com.mod.loan.itf.sms.moxintong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.UserSmsMapper;
import com.mod.loan.model.UserSms;
import com.mod.loan.util.HttpUtils;
import com.mod.loan.util.MD5;
import com.mod.loan.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kk
 */
@Component
public class MoXinTongSms {
    private static final Logger log = LoggerFactory.getLogger(MoXinTongSms.class);

    private static String url = "http://api.uoleem.com.cn/sms/httpBatchSend";

    @Resource
    UserSmsMapper smsMapper;

    /**
     * 普通短信
     */
    public boolean send(QueueSmsMessage smsMessage) throws Exception {
        String ts = TimeUtils.parseTime(new Date(), TimeUtils.dateformat5);

        Map<String, String> data = new HashMap<>(5);
        MoXinTongSmsSign sign = MoXinTongSmsSign.getSign(smsMessage.getType());
        data.put("username", sign.getAccesskey());
        data.put("pwd", MD5.toMD5(sign.getAccesskey() + sign.getSecret() + ts));
        data.put("mobile", smsMessage.getPhone());
        if (null != smsMessage.getParams()) {
            //短信标题
            data.put("content", Constant.smsType + smsMessage.getParams());
        }
        data.put("ts", ts);
        String body = HttpUtils.doPost(url, data);
        JSONObject o = JSON.parseObject(body);
        if (0 == (int) o.get("code")) {
            //记录发送成功的次数
            UserSms sms = new UserSms();
            sms.setMobile(smsMessage.getPhone());
            sms.setCreateTime(new Date());
            smsMapper.insert(sms);
            return true;
        } else {
            log.error("短信发送失败,message={},respone={}", JSON.toJSON(smsMessage), body);
            return false;
        }
    }
}
