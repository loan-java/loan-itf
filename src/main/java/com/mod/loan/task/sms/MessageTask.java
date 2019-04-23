package com.mod.loan.task.sms;

import com.mod.loan.common.enums.SmsTemplate;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.service.OrderService;
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
                QueueSmsMessage smsMessage = new QueueSmsMessage();
                smsMessage.setClientAlias(order.get("merchant").toString());
                smsMessage.setType(SmsTemplate.T2003.getKey());
                smsMessage.setPhone(order.get("userPhone").toString());
                rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
            }

        } catch (Exception e) {
            logger.error("明日逾期短信发送异常", e);
        }
    }

    /**
     * 应还时间当天提醒 中午11点30提醒
     */
    @Scheduled(cron = "0 0 10,18 * * ?")
    public void overdueToday() {
        List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(31, "=", 0);
        for (Map<String, Object> order : orderList) {
            QueueSmsMessage smsMessage = new QueueSmsMessage();
            smsMessage.setClientAlias(order.get("merchant").toString());
            smsMessage.setType(SmsTemplate.T2002.getKey());
            smsMessage.setPhone(order.get("userPhone").toString());
            rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
        }
    }

}
