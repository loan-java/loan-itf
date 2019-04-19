package com.mod.loan.itf.voice.lianxin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueVoiceMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.model.MessageVoice;
import com.mod.loan.util.MD5;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Component
public class LianXinVoice {

    private static final Logger log = LoggerFactory.getLogger(LianXinVoice.class);
    private static String url="http://yypt.51nche.com/voice/notice/send.do";

    //语音通知
    public static MessageVoice send(QueueVoiceMessage voiceMessage, String projectAlias) throws Exception {

		LianXinEnum lianXinEnum = LianXinEnum.getLianXinParam(projectAlias);
		if (lianXinEnum == null) {
			log.error("东方联信语音签名为空，projectAlias={}", projectAlias);
			throw new Exception("东方联信语音签名为空");
		}
    	
        MessageVoice messageVoice = new MessageVoice();
        messageVoice.setStatus(0);//初始化
        messageVoice.setType(2);
        messageVoice.setMerchant(voiceMessage.getClientAlias());
        messageVoice.setUid(NumberUtils.toLong(voiceMessage.getUid()));
        messageVoice.setUserPhone(voiceMessage.getPhone());
        messageVoice.setOrderId(NumberUtils.toLong(voiceMessage.getOrderId()));
        messageVoice.setCreateTime(new Date());
        messageVoice.setUpdateTime(new Date());

        HashMap<String, String> data = new HashMap<>(16);
        data.put("cusNo", lianXinEnum.getCusNo());
        data.put("called", voiceMessage.getPhone());
        //通知模板的类型：1文本模板 2语音文件
        data.put("voiceTemplateType", "1");
        data.put("voiceTemplateId", lianXinEnum.getTemplateId());
        if(voiceMessage.getParams().equals("")){
            data.put("varContent", voiceMessage.getParams());
        }else{
            data.put("varContent", voiceMessage.getParams().replaceAll("\\|", ","));
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        data.put("timestamp", timestamp);
        String sign = MD5.toMD5(lianXinEnum.getCusNo() + voiceMessage.getPhone() + timestamp + lianXinEnum.getKey());
        data.put("sign", sign);
        data.put("taskId", Constant.ENVIROMENT+"_"+voiceMessage.getOrderId());
        //人声设置 voiceTemplateType=1时生效： 0女生 1男生
        data.put("voiceType", "0");

        Connection.Response response = Jsoup.connect(url).method(Connection.Method.POST).data(data).ignoreContentType(true).execute();
        String body = response.body();
        JSONObject jsonObject = JSON.parseObject(body);
        messageVoice.setRemark(jsonObject.get("retmsg").toString());
        messageVoice.setUpdateTime(new Date());
        if ("0".equals(jsonObject.get("retcode").toString())) {
            messageVoice.setStatus(1);//受理成功
            messageVoice.setCallId(jsonObject.get("retdata").toString());
            return messageVoice;
        } else {
            messageVoice.setStatus(4);//受理失败
            messageVoice.setCallId("failed_" + UUID.randomUUID().toString());
            log.error("语音发送失败,message={},respone={}", JSON.toJSON(voiceMessage), body);
            return messageVoice;
        }
    }
    public static void main(String[] args) {
    	QueueVoiceMessage voiceMessage=new QueueVoiceMessage();
    	voiceMessage.setClientAlias("suixinghua");
    	voiceMessage.setOrderId("1");
    	voiceMessage.setPhone("15168650242");
    	voiceMessage.setType("10001");
    	voiceMessage.setParams("小闫|麻利回收");
    	try {
			MessageVoice send = LianXinVoice.send(voiceMessage,"jingyu");
			System.out.println(JSON.toJSONString(send));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
