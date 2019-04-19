package com.mod.loan.itf.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.itf.sms.chuanglan.ChuangLanSms;
import com.mod.loan.itf.sms.chuangrui.ChuangRuiSms;
import com.mod.loan.itf.sms.mifeng.MiFengSms;
import com.mod.loan.itf.sms.miyun.MiYunSms;
import com.mod.loan.itf.sms.yunpian.YunPianSms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SmsConsumer {
	private static final Logger log = LoggerFactory.getLogger(SmsConsumer.class);

	@RabbitListener(queues = "queue_sms", containerFactory = "sms_factory")
	@RabbitHandler
	public void process(Message mess) {
		QueueSmsMessage smsMessage = JSONObject.parseObject(mess.getBody(), QueueSmsMessage.class);

		try {
			switch (smsMessage.getType()) {
			case "1001":
				boolean flag_1001 = ChuangLanSms.send(smsMessage);
				if (!flag_1001) {
					ChuangRuiSms.send(smsMessage);
				}
				break;
			case "1002":
				boolean flag_1002 = ChuangLanSms.send(smsMessage);
				if (!flag_1002) {
					ChuangRuiSms.send(smsMessage);
				}
				break;
			case "2004":
				boolean flag_2004 = MiFengSms.send(smsMessage);
				if (!flag_2004) {
					YunPianSms.send(smsMessage);
				}
				break;
			case "2001":
                boolean flag_2001 = MiFengSms.send(smsMessage);
                if (!flag_2001) {
                    YunPianSms.send(smsMessage);
                }
				break;
			default:
				boolean flag = MiFengSms.send(smsMessage);
				if (!flag) {
					YunPianSms.send(smsMessage);
				}
			}
		} catch (Exception e) {
			log.error("短信发送异常,请求参数为{}", JSON.toJSONString(smsMessage));
			log.error("短信发送异常", e);
		}
	}

	@Bean("sms_factory")
	public SimpleRabbitListenerContainerFactory pointTaskContainerFactoryLoan(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(2);
		factory.setConcurrentConsumers(20);
		return factory;
	}
	
}
