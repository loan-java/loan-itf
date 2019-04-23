//package com.mod.loan.itf.sms.old.yunpian;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.message.QueueSmsMessage;
//import org.jsoup.Connection.Method;
//import org.jsoup.Jsoup;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class YunPianSms {
//	private static final Logger log = LoggerFactory.getLogger(YunPianSms.class);
//	private static String url="https://sms.yunpian.com/v2/sms/single_send.json";
//	// 普通短信
//	public static boolean send(QueueSmsMessage smsMessage) throws IOException  {
//		YunPianSmsSign sign = YunPianSmsSign.getSign(smsMessage.getClientAlias());
//		YunPianSmsTemplate template = YunPianSmsTemplate.getTemplate(smsMessage.getType());
//		String params = sign.getSignName()+"|"+smsMessage.getParams();
//		String[] arr = params.split("\\|");
//		String msg = String.format(template.getContent(),arr);
//		//发送短信API
//		Map<String, String> param = new HashMap<>();
//		param.put("apikey", sign.getAccesskey());
//		param.put("mobile", smsMessage.getPhone());
//		param.put("text", msg);
//		String body = Jsoup.connect(url).data(param).method(Method.POST).ignoreContentType(true).ignoreHttpErrors(true).timeout(5000).execute().body();
//		if("0".equals(JSONObject.parseObject(body).getString("code"))){
//			return true;
//		}
//		log.error("短信发送失败,message={},respone={}", JSON.toJSON(smsMessage),body);
//		return false;
//	}
//
//}
