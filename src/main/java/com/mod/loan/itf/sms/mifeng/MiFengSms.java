package com.mod.loan.itf.sms.mifeng;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MiFengSms {
	private static final Logger logger = LoggerFactory.getLogger(MiFengSms.class);
	private static final String POST_URL = "https://message-api.fintechzh.com/sms/v1/sms/single_send";

	public static boolean send(QueueSmsMessage smsMessage) {
		MiFengSmsSign sign = MiFengSmsSign.getSign(smsMessage.getClientAlias());
		MiFengTemplate template = MiFengTemplate.getTemplate(smsMessage.getType());
		String params = sign.getSignName()+"|"+smsMessage.getParams();
		String[] arr = params.split("\\|");
		String msg = String.format(template.getContent(),arr);

		Map<String, String> data=new HashMap<>();
		data.put("account",sign.getAccesskey());
		data.put("sign",sign.getSecret());
		data.put("text",msg);
		data.put("mobile", smsMessage.getPhone());
		data.put("extend", sign.getSignId());

		String body = HttpUtils.doPost(POST_URL, JSONObject.toJSONString(data),"UTF-8");

		if("0".equals(JSONObject.parseObject(body).getString("code"))){
			return true;
		}
		logger.error("蜜蜂发送短信异常，返回信息为{}=",body);
		return false;
	}

	public static void main(String[] args) throws Exception {
//		QueueSmsMessage smsMessage = new QueueSmsMessage("jingyu", "2003", "15867130607",null);
		QueueSmsMessage smsMessage = new QueueSmsMessage("malihuishou", "2003", "15867130607","");
		try {
			System.out.println(MiFengSms.send(smsMessage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
