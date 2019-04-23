//package com.mod.loan.itf.moxie.consumer;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.common.message.MoxieMobileMessage;
//import com.mod.loan.mapper.MoxieMobileMapper;
//import com.mod.loan.model.MoxieMobile;
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
//public class MobileConsumer {
//
//	private static final Logger logger = LoggerFactory.getLogger(MobileConsumer.class);
//
//	@Autowired
//	RabbitTemplate rabbitTemplate;
//	@Autowired
//	MoxieMobileMapper moxieMobileMapper;
//	@Value("${moxie.token}")
//	String token;
//
//	@RabbitListener(queues = "queue_moxie_mobile", containerFactory = "moxie_mobile")
//	@RabbitHandler
//	public void moxie_mobile(Message mess) {
//		MoxieMobileMessage moxie_mobile = JSONObject.parseObject(mess.getBody(), MoxieMobileMessage.class);
//		if (moxie_mobile.getId() == null || moxie_mobile.getTaskId().equals(null)) {
//			logger.error("运营商消息异常={}", JSON.toJSONString(moxie_mobile));
//			return;
//		}
//		try {
//			MoxieMobile moxie = moxieMobileMapper.selectByPrimaryKey(moxie_mobile.getId());
//			if (moxie.getTag().intValue() != 0 || !moxie_mobile.getTaskId().equals(moxie.getTaskId())) {
//				return;
//			}
//			MoxieOssUtil.uploadMobileJxlReport(token, moxie.getTaskId(), moxie.getPhone());
//			MoxieOssUtil.uploadMobileMxdata(token, moxie.getTaskId(), moxie.getPhone());
//			MoxieMobile record = new MoxieMobile();
//			record.setId(moxie_mobile.getId());
//			record.setTag(1);
//			moxieMobileMapper.updateByPrimaryKeySelective(record);
//		} catch (Exception e) {
//			logger.error("上传运营商数据失败=" + JSON.toJSONString(moxie_mobile), e);
//		}
//
//	}
//
//	@Bean("moxie_mobile")
//	public SimpleRabbitListenerContainerFactory pointTaskContainerFactoryLoan(ConnectionFactory connectionFactory) {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		factory.setPrefetchCount(1);
//		factory.setConcurrentConsumers(10);
//		return factory;
//	}
//}
