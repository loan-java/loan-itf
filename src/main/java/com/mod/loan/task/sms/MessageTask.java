package com.mod.loan.task.sms;

import com.mod.loan.common.enums.SmsTemplate;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.model.Order;
import com.mod.loan.service.OrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageTask {

    public static final Logger logger = LoggerFactory.getLogger(MessageTask.class);
    @Autowired
    OrderService orderService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 提前一天还款短信提醒 下午17点
     */
    @Scheduled(cron = "0 0 17 * * ?")
    public void overdueTomorrow() {
        try {
            List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(31, "=", -1);
            for (Map<String, Object> order : orderList) {
                Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
                QueueSmsMessage smsMessage = new QueueSmsMessage();
                smsMessage.setClientAlias(order.get("merchant").toString());
                smsMessage.setType(SmsTemplate.T2003.getKey());
                smsMessage.setPhone(order.get("userPhone").toString());
                smsMessage.setParams(Constant.smsTitle + "你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "元，即将到期，请及时还款！");
                rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
            }

        } catch (Exception e) {
            logger.error("明日逾期短信发送异常", e);
        }
    }

    /**
     * 应还时间当天提醒 10点和18点提醒
     */
    @Scheduled(cron = "0 0 10,18 * * ?")
    public void overdueToday() {
        List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(31, "=", 0);
        for (Map<String, Object> order : orderList) {
            Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
            QueueSmsMessage smsMessage = new QueueSmsMessage();
            smsMessage.setClientAlias(order.get("merchant").toString());
            smsMessage.setType(SmsTemplate.T2002.getKey());
            smsMessage.setPhone(order.get("userPhone").toString());
            smsMessage.setParams(Constant.smsTitle + "你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "元，今日到期，请及时还款！");
            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
        }
    }


    /**
     * 逾期提醒 每天10点和18点提醒
     */
    @Scheduled(cron = "0 0 10,18 * * ?")
    public void overdue() {
        List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(33, ">", 0);
        for (Map<String, Object> order : orderList) {
            Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
            QueueSmsMessage smsMessage = new QueueSmsMessage();
            smsMessage.setClientAlias(order.get("merchant").toString());
            smsMessage.setType(SmsTemplate.T2005.getKey());
            smsMessage.setPhone(order.get("userPhone").toString());
            smsMessage.setParams(Constant.smsTitle + "你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "已逾期，避免信用损失请尽快还款！");
            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
        }
    }

}
