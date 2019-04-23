//package com.mod.loan.itf.voice;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.enums.SmsTemplate;
//import com.mod.loan.common.message.QueueSmsMessage;
//import com.mod.loan.config.rabbitmq.RabbitConst;
//import com.mod.loan.mapper.MessageVoiceMapper;
//import com.mod.loan.model.MessageVoice;
//import com.mod.loan.util.TimeUtils;
//
//
//@RestController
//public class VoiceCallBackController {
//    private static final Logger log = LoggerFactory.getLogger(VoiceCallBackController.class);
//    @Autowired
//    private MessageVoiceMapper messageVoiceMapper;
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    @RequestMapping("/voice_callback_lianxin")
//    public Map voice_callback_lianxin(String taskId, String hangupReason, String billId) {
//
//        Map<String,String> map = new HashMap<String,String>();
//
//        try {
//            MessageVoice messageVoice = messageVoiceMapper.findLatestByCallId(billId);
//            if (null == messageVoice) {
//                log.info("语音回调异常，该话单不存在，异常订单为{}", taskId);
//                map.put("code","1000");
//                map.put("retmsg","失败");
//                return map;
//            }
//
//            MessageVoice messageVoice1 = new MessageVoice();
//            messageVoice1.setId(messageVoice.getId());
//            messageVoice1.setUpdateTime(new Date());
//            if ("16".equals(hangupReason)) {
//                messageVoice1.setStatus(2);
//                messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//                map.put("code","0000");
//                map.put("retmsg","成功");
//                return map;
//            }
//            messageVoice1.setStatus(3);
//            messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//
//			// 当前回调话单是否属于当前日期
//			String nowDate = TimeUtils.parseTime(new Date(), TimeUtils.dateformat2);
//			String messageVoiceTime = TimeUtils.parseTime(messageVoice.getCreateTime(), TimeUtils.dateformat2);
//			if (!nowDate.equals(messageVoiceTime)) {
//				log.error("语音回调异常，该话单创建时间为{}，异常订单为{}", messageVoiceTime, taskId);
//				map.put("code","1000");
//                map.put("retmsg","失败");
//				return map;
//			}
//
//            QueueSmsMessage smsMessage = new QueueSmsMessage();
//            smsMessage.setClientAlias(messageVoice.getMerchant());
//            smsMessage.setType(SmsTemplate.T2002.getKey());
//            smsMessage.setPhone(messageVoice.getUserPhone());
//            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
//            map.put("code","1000");
//            map.put("retmsg","失败");
//
//        } catch (Exception e) {
//            log.error("语音回调失败,订单号为", taskId);
//            map.put("code","1000");
//            map.put("retmsg","失败");
//        }
//        return map;
//    }
//
//    @RequestMapping("/voice_callback_tencent")
//    public void voice_callback_tencent(@RequestBody(required = true) String voiceprompt_callback) {
//        if (voiceprompt_callback.contains("voice_failure_callback") || voiceprompt_callback.contains("voicekey_callback")) {
//            return;
//        }
//        try {
//            JSONObject jsonObject = JSON.parseObject(voiceprompt_callback);
//            String data = jsonObject.get("voiceprompt_callback").toString();
//            JSONObject json = JSON.parseObject(data);
//            //错误码，Enum{0： 用户正常接听 1： 用户未接听 2： 呼叫异常}
//            String result = json.get("result").toString();
//            String callid = json.get("callid").toString();
//            MessageVoice messageVoice = messageVoiceMapper.findLatestByCallId(callid);
//            if (null == messageVoice) {
//                log.info("语音回调异常，该话单不存在，异常订单为{}", callid);
//                return;
//            }
//            MessageVoice messageVoice1 = new MessageVoice();
//            messageVoice1.setId(messageVoice.getId());
//            messageVoice1.setUpdateTime(new Date());
//            if ("0".equals(result)) {
//                messageVoice1.setStatus(2);
//                messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//                return;
//            }
//            messageVoice1.setStatus(3);
//            messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//
//			// 当前回调话单是否属于当前日期
//			String nowDate = TimeUtils.parseTime(new Date(), TimeUtils.dateformat2);
//			String messageVoiceTime = TimeUtils.parseTime(messageVoice.getCreateTime(), TimeUtils.dateformat2);
//			if (!nowDate.equals(messageVoiceTime)) {
//				log.error("语音回调异常，该话单创建时间为{}，异常订单为{}", messageVoiceTime, callid);
//				return;
//			}
//
//            QueueSmsMessage smsMessage = new QueueSmsMessage();
//            smsMessage.setClientAlias(messageVoice.getMerchant());
//            smsMessage.setType(SmsTemplate.T2002.getKey());
//            smsMessage.setPhone(messageVoice.getUserPhone());
//            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
//        } catch (Exception e) {
//            log.error("腾讯回调失败，回调信息=" + voiceprompt_callback, e);
//        }
//    }
//
//    @RequestMapping("/voice_callback_mayun")
//    public Map voice_callback_mayun(@RequestBody String requestBody) {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("respCode","00000");
//        JSONObject body = JSON.parseObject(requestBody);
//        String callId = body.getString("callId");
//        String state = body.getString("state");
//        try {
//            MessageVoice messageVoice = messageVoiceMapper.findLatestByCallId(callId);
//            if (null == messageVoice) {
//                log.info("语音回调异常，该话单不存在，异常订单为{}", callId);
//                return map;
//            }
//
//            MessageVoice messageVoice1 = new MessageVoice();
//            messageVoice1.setId(messageVoice.getId());
//            messageVoice1.setUpdateTime(new Date());
//            if ("0".equals(state)) {
//                messageVoice1.setStatus(2);
//                messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//                return map;
//            }
//            messageVoice1.setStatus(3);
//            messageVoiceMapper.updateByPrimaryKeySelective(messageVoice1);
//
//			// 当前回调话单是否属于当前日期
//			String nowDate = TimeUtils.parseTime(new Date(), TimeUtils.dateformat2);
//			String messageVoiceTime = TimeUtils.parseTime(messageVoice.getCreateTime(), TimeUtils.dateformat2);
//			if (!nowDate.equals(messageVoiceTime)) {
//				log.error("语音回调异常，该话单创建时间为{}，异常订单为{}", messageVoiceTime, callId);
//				return map;
//			}
//
//            QueueSmsMessage smsMessage = new QueueSmsMessage();
//            smsMessage.setClientAlias(messageVoice.getMerchant());
//            smsMessage.setType(SmsTemplate.T2002.getKey());
//            smsMessage.setPhone(messageVoice.getUserPhone());
//            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
//        } catch (Exception e) {
//            log.error("语音回调失败,订单号为", callId);
//        }
//        return map;
//    }
//}
