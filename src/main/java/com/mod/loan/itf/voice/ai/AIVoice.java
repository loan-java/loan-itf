//package com.mod.loan.itf.voice.ai;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.message.QueueVoiceMessage;
//import com.mod.loan.config.Constant;
//import com.mod.loan.itf.voice.tencent.EnumTencentTemplate;
//import com.mod.loan.model.MessageVoice;
//import com.mod.loan.util.HttpUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * 备注：
// * Ai语音
// * 发送ip需要找技术人员添加;
// * 没有后台查看发送记录
// */
//@Component
//public class AIVoice {
//
//    private static final Logger log = LoggerFactory.getLogger(AIVoice.class);
//
//    public static String linkurl = "http://notice.hzdaba.cn:8088/oper";
//    public static String userId = "44ef10e1-bd4c-4a70-b6d6-6cd1e7b70da4";
//
//    public static void main(String[] args) {
//    	QueueVoiceMessage voiceMessage=new QueueVoiceMessage();
//    	voiceMessage.setClientAlias("malihuishou");
//    	voiceMessage.setOrderId("1");
//    	voiceMessage.setPhone("15168650242");
//    	voiceMessage.setType("10001");
//    	voiceMessage.setParams("小闫|麻利app");
//    	try {
//			System.out.println(AIVoice.send(voiceMessage));
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
//        String content = "";
//        EnumAITemplate template = EnumAITemplate.getTemplate(voiceMessage.getType());
//        if(StringUtils.isBlank(voiceMessage.getParams())){
//            content = template.getContent();
//        }else if (voiceMessage.getType().equals(EnumTencentTemplate.V1001.getKey())){
//            String[] arr = voiceMessage.getParams().split("\\|");
//            content=String.format(template.getContent(),arr);
//        }
//
//        JSONObject data = new JSONObject();
//        data.put("id", UUID.randomUUID().toString().replaceAll("-",""));
//        data.put("userId", userId);
//        data.put("action", "1001");//操作功能
//
//        JSONArray tos = new JSONArray();
//        Map<String,String> body = new HashMap<String,String>();
//        body.put("reqId",UUID.randomUUID().toString());
//        body.put("to",voiceMessage.getPhone());
//        body.put("verifyCode",""); //通知内容 可选
//        body.put("lang","5000");
//        body.put("playMode","tts");
//        body.put("playVerifyCode",content);
//        tos.add(body);
//        data.put("tos", tos);
//        data.put("respUrl", Constant.SERVER_ITF_URL+"voice_callback_ai");
//        data.put("noticeMode", 6002);
//        data.put("displayNum", "8889");
//        data.put("playTimes", 3);
//        data.put("needDtmf", "3000"); //代表yes 可选
//
//        log.info("请求信息为：{}" , data.toJSONString());
//        String res = HttpUtils.doPost(linkurl,JSONObject.toJSONString(data),"utf-8");
//        log.info("响应信息为：{}" , res);
//        JSONObject jsonObject = JSON.parseObject(res);
//        messageVoice.setRemark(jsonObject.get("content").toString());
//        messageVoice.setUpdateTime(new Date());
//        if ("4000".equals(jsonObject.get("status").toString())) {
//            messageVoice.setStatus(1);//受理成功
//            messageVoice.setCallId(jsonObject.get("id").toString());
//            return messageVoice;
//        } else {
//            messageVoice.setCallId(jsonObject.get("id").toString());
//            messageVoice.setStatus(4);//受理失败
//            log.error("语音发送失败,message={},respone={}", JSON.toJSON(voiceMessage), body);
//            return messageVoice;
//        }
//
//
//    }
//}
