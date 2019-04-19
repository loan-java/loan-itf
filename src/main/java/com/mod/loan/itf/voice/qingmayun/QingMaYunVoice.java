package com.mod.loan.itf.voice.qingmayun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueVoiceMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.itf.voice.tencent.EnumTencentTemplate;
import com.mod.loan.model.MessageVoice;
import com.mod.loan.util.HttpUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class QingMaYunVoice {

    private static final Logger log = LoggerFactory.getLogger(QingMaYunVoice.class);

    public static String Soft_Version = "20141029";
    public static String Sid = "8ea3ce6b52104379a4be90a9085a1eb5";
    public static String Token = "bdac20c3f95946898398d11c631921db";
    public static String url = "https://api.qingmayun.com/%s/accounts/%s/call/voiceTemplateNotify?sig=%s&timestamp=%s";

    public static MessageVoice send(QueueVoiceMessage voiceMessage) {
        MessageVoice messageVoice = new MessageVoice();
        messageVoice.setStatus(0);//初始化
        messageVoice.setType(3);
        messageVoice.setMerchant(voiceMessage.getClientAlias());
        messageVoice.setUid(NumberUtils.toLong(voiceMessage.getUid()));
        messageVoice.setUserPhone(voiceMessage.getPhone());
        messageVoice.setOrderId(NumberUtils.toLong(voiceMessage.getOrderId()));
        messageVoice.setCreateTime(new Date());
        messageVoice.setUpdateTime(new Date());

        HashMap<String, String> data = new HashMap<String,String>();
        data.put("callee", voiceMessage.getPhone());
        EnumQingMaYunTemplate template = EnumQingMaYunTemplate.getTemplate(voiceMessage.getType());
        data.put("templateId", template.getTemplateId());
        if (voiceMessage.getType().equals(EnumTencentTemplate.V1001.getKey())) {
            data.put("param",  voiceMessage.getParams().replaceAll("\\|", ","));
        }
        data.put("playTimes", "3");
        data.put("billUrl", Constant.SERVER_ITF_URL+"voice_callback_mayun");
        Long timestamp = System.currentTimeMillis();
        String sig = DigestUtils.md5DigestAsHex(("" + Sid + Token + timestamp).getBytes());
        String linkurl = String.format(url, Soft_Version,Sid,sig,timestamp);
        Map map = new HashMap(4);
        map.put("voiceTemplate",data);
        String res = HttpUtils.doPost(linkurl,JSONObject.toJSONString(map),"utf-8");
        JSONObject json = JSON.parseObject(res);
        if (json.get("result")==null) {
            messageVoice.setCallId("failed_" + UUID.randomUUID().toString());
            messageVoice.setStatus(4);//受理失败
            log.error("语音发送失败,message={},respone={}", JSON.toJSON(voiceMessage), res);
            return messageVoice;
		}
        String result = json.get("result").toString();
        JSONObject jsonObject = JSON.parseObject(result);
        messageVoice.setUpdateTime(new Date());
        if ("00000".equals(jsonObject.get("respCode").toString())) {
            messageVoice.setStatus(1);//受理成功
            messageVoice.setCallId(jsonObject.get("callId").toString());
            return messageVoice;
        } else {
            messageVoice.setRemark(jsonObject.get("message").toString());
            messageVoice.setCallId("failed_" + UUID.randomUUID().toString());
            messageVoice.setStatus(4);//受理失败
            log.error("语音发送失败,message={},respone={}", JSON.toJSON(voiceMessage), res);
            return messageVoice;
        }
    }
    public static void main(String[] args) {
        QueueVoiceMessage voiceMessage = new QueueVoiceMessage();
        voiceMessage.setClientAlias("malihuishou");
        voiceMessage.setOrderId("1");
        voiceMessage.setPhone("15867130607");
        voiceMessage.setType("10001");
        voiceMessage.setParams("小闫|麻利app");
        try {
            MessageVoice send = QingMaYunVoice.send(voiceMessage);
            System.out.println(JSON.toJSONString(send));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
