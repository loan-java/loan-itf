package com.mod.loan.itf.sms.miyun;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MiYunSms {
	private static final Logger log = LoggerFactory.getLogger(MiYunSms.class);
	private static String url="http://api.1cloudsp.com/api/v2/single_send";

	// 普通短信
	public static boolean send(QueueSmsMessage smsMessage) throws Exception {
		Map<String, String> data=new HashMap<>();
		MiYunSmsSign sign = MiYunSmsSign.getSign(smsMessage.getClientAlias());
		data.put("accesskey", sign.getAccesskey());
		data.put("secret", sign.getSecret());
		data.put("sign", sign.getSignId());
		MiYunSmsTemplate template = MiYunSmsTemplate.getTemplate(smsMessage.getType());
		data.put("templateId", template.getTemplateId());
		data.put("mobile", smsMessage.getPhone());
		if(null != smsMessage.getParams()){
			data.put("content", URLEncoder.encode(smsMessage.getParams().replaceAll("\\|", "##"), "utf-8"));
		}

		String body = HttpUtils.doPost(url, data);
		if (body.contains("SUCCESS")) {
			return true;
		}else {
			log.error("短信发送失败,message={},respone={}", JSON.toJSON(smsMessage),body);
			return false;
		}
	}
	public static void main(String[] args) throws Exception {
//		QueueSmsMessage smsMessage = new QueueSmsMessage("malihuishou", "1001", "15168650242","123456|5分钟");
//		QueueSmsMessage smsMessage = new QueueSmsMessage("malihuishou", "2001", "15867130607","100.00|05月11日");
//		QueueSmsMessage smsMessage = new QueueSmsMessage("malihuishou", "2002", "15867130607","");
		QueueSmsMessage smsMessage = new QueueSmsMessage("jingyu", "2002", "15168650242","");
		try {
			System.out.println(MiYunSms.send(smsMessage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
