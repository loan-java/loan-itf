package com.mod.loan.itf.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.itf.sms.moxintong.MoXinTongSms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SmsConsumer {
    private static final Logger log = LoggerFactory.getLogger(SmsConsumer.class);

    @Resource
    MoXinTongSms moXinTongSms;

    @RabbitListener(queues = "queue_sms", containerFactory = "sms_factory")
    @RabbitHandler
    public void process(Message mess) {
        QueueSmsMessage smsMessage = JSONObject.parseObject(mess.getBody(), QueueSmsMessage.class);

        try {
            moXinTongSms.send(smsMessage);
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
