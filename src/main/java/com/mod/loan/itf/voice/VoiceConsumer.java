//package com.mod.loan.itf.voice;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.enums.SmsTemplate;
//import com.mod.loan.common.message.QueueSmsMessage;
//import com.mod.loan.common.message.QueueVoiceMessage;
//import com.mod.loan.config.Constant;
//import com.mod.loan.config.rabbitmq.RabbitConst;
//import com.mod.loan.config.redis.RedisConst;
//import com.mod.loan.config.redis.RedisMapper;
//import com.mod.loan.itf.voice.lianxin.LianXinVoice;
//import com.mod.loan.itf.voice.qingmayun.QingMaYunVoice;
//import com.mod.loan.mapper.MessageVoiceMapper;
//import com.mod.loan.model.MessageVoice;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VoiceConsumer {
//	private static final Logger log = LoggerFactory.getLogger(VoiceConsumer.class);
//
//	@Autowired
//	private MessageVoiceMapper messageVoiceMapper;
//	@Autowired
//	RabbitTemplate rabbitTemplate;
//	@Autowired
//	RedisMapper redisMapper;
//	@Value("${server.itf.url}")
//	String server_itf_url;
//
//	@RabbitListener(queues = "queue_voice",containerFactory = "voice_process")
//	@RabbitHandler
//	public void voice_process(Message mess) {
//		QueueVoiceMessage voiceMessage = JSONObject.parseObject(mess.getBody(),QueueVoiceMessage.class);
//		try {
//			//5分钟之内同一号码被锁住，存入死信队列
//			if (!redisMapper.lock(RedisConst.VOICE_LOCK+voiceMessage.getPhone(), 300)) {
//					//同一手机号5分钟之内发送过，将消息存入死信队列，5分钟后再次发送提醒语音
//					rabbitTemplate.convertAndSend(RabbitConst.queue_voice_wait, voiceMessage);
//					return;
//			}
////			MessageVoice messageVoice = LianXinVoice.send(voiceMessage, Constant.PROJECT_ALIAS);
//			MessageVoice messageVoice = QingMaYunVoice.send(voiceMessage);
//			messageVoiceMapper.insertSelective(messageVoice);
//			if(messageVoice.getStatus() == 4){
//				QueueSmsMessage smsMessage = new QueueSmsMessage();
//				smsMessage.setClientAlias(messageVoice.getMerchant());
//				smsMessage.setType(SmsTemplate.T2002.getKey());
//				smsMessage.setPhone(messageVoice.getUserPhone());
//				rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
//			}
//		} catch (Exception e) {
//			log.error("语音发送异常={}", JSON.toJSONString(voiceMessage));
//			log.error("语音发送异常", e);
//		}
//	}
//
//	@Bean("voice_process")
//    public SimpleRabbitListenerContainerFactory pointTaskContainerFactoryLoan(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPrefetchCount(1);
//        factory.setConcurrentConsumers(1);
//        return factory;
//    }
//
//}
