package com.mod.loan.itf.risk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.RiskAuditResultMessage;
import com.mod.loan.model.Order;
import com.mod.loan.service.OrderAuditService;
import com.mod.loan.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderAuditResultConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderAuditResultConsumer.class);

    @Autowired
    OrderService orderService;
    @Autowired
    OrderAuditService orderAuditService;


    @RabbitListener(queues = "queue_risk_order_result",containerFactory="auditResultContainer")
    @RabbitHandler
    public void risk_order_result(Message mess) {
        RiskAuditResultMessage message = JSONObject.parseObject(mess.getBody(),RiskAuditResultMessage.class);
        try {
            Order order = orderService.selectByPrimaryKey(message.getOrderId());
            if(11 != order.getStatus()){
                return;
            }
            if (1 == message.getStatus() ){ //1-通过，直接进入放款
                Order order1 = new Order();
                order1.setId(order.getId());
                order1.setStatus(21);
                order1.setAuditTime(new Date());
                orderService.updateByPrimaryKeySelective(order1);
                return;
            }
            if (2 == message.getStatus() ){ //2-通过，进入人审
                Order order1 = new Order();
                order1.setId(order.getId());
                order1.setStatus(12);
                order1.setAuditTime(new Date());
                orderService.updateByPrimaryKeySelective(order1);
                return;
            }
            if (3 == message.getStatus() ){ //3-拒绝
                Order order1 = new Order();
                order1.setId(order.getId());
                order1.setStatus(51);
                order1.setAuditTime(new Date());
                orderService.updateByPrimaryKeySelective(order1);
                return;
            }
        } catch (Exception e) {
            logger.error("消费订单审核结果队列异常:订单信息为{} ", JSON.toJSONString(message));
            logger.error("消费订单审核结果队列异常:{} ", e);
        }
    }

    @Bean("auditResultContainer")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactoryLoan( ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(1); 
        factory.setConcurrentConsumers(1);
        return factory;
    }
}
