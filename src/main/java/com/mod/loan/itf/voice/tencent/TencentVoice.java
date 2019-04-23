//package com.mod.loan.itf.voice.tencent;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.github.qcloudsms.SmsVoicePromptSender;
//import com.github.qcloudsms.SmsVoicePromptSenderResult;
//import com.mod.loan.common.message.QueueVoiceMessage;
//import com.mod.loan.model.MessageVoice;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Component
//public class TencentVoice {
//
//    private static final Logger log = LoggerFactory.getLogger(TencentVoice.class);
//
//    public static int appid = 1400108486;
//    public static String appkey = "38dc34f640aacde1bde1465b398a8e25";
//
//    public static void main(String[] args) {
//    	QueueVoiceMessage voiceMessage=new QueueVoiceMessage();
//    	voiceMessage.setClientAlias("malihuishou");
//    	voiceMessage.setOrderId("1");
//    	voiceMessage.setPhone("15168650242");
//    	voiceMessage.setType("10001");
//    	voiceMessage.setParams("吴光宇|麻利app");
//    	try {
//			MessageVoice send = TencentVoice.send(voiceMessage);
//			System.out.println(JSON.toJSONString(send));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//    public static MessageVoice send(QueueVoiceMessage voiceMessage) throws Exception {
//
//        MessageVoice messageVoice = new MessageVoice();
//        messageVoice.setStatus(0);//初始化
//        messageVoice.setType(1);
//        messageVoice.setMerchant(voiceMessage.getClientAlias());
//        messageVoice.setUid(NumberUtils.toLong(voiceMessage.getUid()));
//        messageVoice.setUserPhone(voiceMessage.getPhone());
//        messageVoice.setOrderId(NumberUtils.toLong(voiceMessage.getOrderId()));
//        messageVoice.setCreateTime(new Date());
//        messageVoice.setUpdateTime(new Date());
//
//        String promptfile = "";
//        EnumTencentTemplate template = EnumTencentTemplate.getTemplate(voiceMessage.getType());
//        if(StringUtils.isBlank(voiceMessage.getParams())){
//            promptfile = template.getContent();
//        }else if (voiceMessage.getType().equals(EnumTencentTemplate.V1001.getKey())){
//            String[] arr = voiceMessage.getParams().split("\\|");
//            promptfile=String.format(template.getContent(),arr);
//        }
//        SmsVoicePromptSender sender = new SmsVoicePromptSender(appid, appkey);
//        SmsVoicePromptSenderResult result = sender.send("86", voiceMessage.getPhone(), 2, 2, promptfile, "");
//        String body = result.toString();
////        log.info("受理结果：{}",body);
//        JSONObject jsonObject = JSON.parseObject(body);
//        messageVoice.setRemark(jsonObject.get("errmsg").toString());
//        messageVoice.setUpdateTime(new Date());
//        if ("0".equals(jsonObject.get("result").toString())) {
//            messageVoice.setStatus(1);//受理成功
//            messageVoice.setCallId(jsonObject.get("callid").toString());
//            return messageVoice;
//        } else {
//            messageVoice.setCallId("failed_"+ UUID.randomUUID().toString());
//            messageVoice.setStatus(4);//受理失败
//            log.error("语音发送失败,message={},respone={}", JSON.toJSON(voiceMessage), body);
//            return messageVoice;
//        }
//    }
//}
