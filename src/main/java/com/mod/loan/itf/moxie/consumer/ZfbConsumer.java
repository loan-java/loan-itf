//package com.mod.loan.itf.moxie.consumer;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.message.MoxieZfbMessage;
//import com.mod.loan.mapper.MoxieZfbMapper;
//import com.mod.loan.model.MoxieZfb;
//import com.mod.loan.util.MoxieOssUtil;
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
//public class ZfbConsumer {
//
//	private static final Logger logger = LoggerFactory.getLogger(ZfbConsumer.class);
//
//	@Autowired
//	RabbitTemplate rabbitTemplate;
//	@Autowired
//	MoxieZfbMapper moxieZfbMapper;
//	@Value("${moxie.token}")
//	String token;
//	@Value("${environment}")
//	String env;
//
//	@RabbitListener(queues = "queue_moxie_zfb", containerFactory = "moxie_zfb")
//	@RabbitHandler
//	public void moxie_zfb(Message mess) {
//		MoxieZfbMessage moxie_zfb = JSONObject.parseObject(mess.getBody(), MoxieZfbMessage.class);
//		if (moxie_zfb.getId() == null || moxie_zfb.getTaskId().equals(null)) {
//			logger.error("支付宝消息异常={}", JSON.toJSONString(moxie_zfb));
//			return;
//		}
//		try {
//			MoxieZfb zfb = moxieZfbMapper.selectByPrimaryKey(moxie_zfb.getId());
//			if (zfb.getTag().intValue() != 0 || !moxie_zfb.getTaskId().equals(zfb.getTaskId())) {
//				return;
//			}
//			MoxieOssUtil.uploadZfbdata(token, zfb.getTaskId());
//			MoxieZfb record = new MoxieZfb();
//			record.setId(moxie_zfb.getId());
//			record.setTag(1);
//			moxieZfbMapper.updateByPrimaryKeySelective(record);
//		} catch (Exception e) {
//			logger.error("上传支付宝数据失败=" + JSON.toJSONString(moxie_zfb), e);
//		}
//
//	}
//
//	@Bean("moxie_zfb")
//	public SimpleRabbitListenerContainerFactory pointTaskContainerFactoryLoan(ConnectionFactory connectionFactory) {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		factory.setPrefetchCount(1);
//		factory.setConcurrentConsumers(5);
//		return factory;
//	}
//}
