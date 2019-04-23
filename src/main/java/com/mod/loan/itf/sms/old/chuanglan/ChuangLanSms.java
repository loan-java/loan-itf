//package com.mod.loan.itf.sms.old.chuanglan;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.message.QueueSmsMessage;
//import com.mod.loan.util.HttpUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class ChuangLanSms {
//	private static final Logger logger = LoggerFactory.getLogger(ChuangLanSms.class);
//	private static final String POST_URL = "https://smssh1.253.com/msg/send/json";
//
//	public static boolean send(QueueSmsMessage smsMessage) {
//		ChuangLanSmsSign sign = ChuangLanSmsSign.getSign(smsMessage.getClientAlias());
//		ChuangLanTemplate template = ChuangLanTemplate.getTemplate(smsMessage.getType());
//		String params = sign.getSignName()+"|"+smsMessage.getParams();
//		String[] arr = params.split("\\|");
//		String msg = String.format(template.getContent(),arr);
//
//		Map<String, String> data=new HashMap<>();
//		data.put("account",sign.getAccesskey());
//		data.put("password",sign.getSecret());
//		data.put("msg",msg);
//		data.put("phone", smsMessage.getPhone());
//		data.put("extend", sign.getSignId());
//
//		String body = HttpUtils.doPost(POST_URL, JSONObject.toJSONString(data),"UTF-8");
//
//		if("0".equals(JSONObject.parseObject(body).getString("code"))){
//			return true;
//		}
//		logger.error("创蓝发送短信异常，返回信息为{}=",body.toString());
//		return false;
//	}
//
//	public static void main(String[] args) throws Exception {
////		QueueSmsMessage smsMessage = new QueueSmsMessage("jingyu", "2003", "15867130607",null);
//		QueueSmsMessage smsMessage = new QueueSmsMessage("malihuishou", "1001", "15867130607","123456|10分钟");
//		try {
//			System.out.println(ChuangLanSms.send(smsMessage));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
