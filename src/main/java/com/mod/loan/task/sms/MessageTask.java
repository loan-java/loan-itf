package com.mod.loan.task.sms;

import com.mod.loan.common.enums.SmsTemplate;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.model.Order;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.ThreadPoolUtils;
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
     * 提前一天还款短信提醒 中午12点05分
     */
    @Scheduled(cron = "0 05 12 * * ?")
    public void overdueTomorrow() {
        try {
            List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(31, "=", -1);
            for (Map<String, Object> order : orderList) {
                ThreadPoolUtils.executor.execute(() -> {
                    Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
                    QueueSmsMessage smsMessage = new QueueSmsMessage();
                    smsMessage.setClientAlias(order.get("merchant").toString());
                    smsMessage.setType(SmsTemplate.T005.getKey());
                    smsMessage.setPhone(order.get("userPhone").toString());
                    smsMessage.setParams("你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "元，即将到期，请及时还款！");
                    rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
                });
            }
        } catch (Exception e) {
            logger.error("明日逾期短信发送异常", e);
        }
    }

    /**
     * 应还时间当天提醒 中午12点10分提醒
     */
    @Scheduled(cron = "0 10 12 * * ?")
    public void overdueToday() {
        try {
            List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(31, "=", 0);
            for (Map<String, Object> order : orderList) {
                ThreadPoolUtils.executor.execute(() -> {
                    Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
                    QueueSmsMessage smsMessage = new QueueSmsMessage();
                    smsMessage.setClientAlias(order.get("merchant").toString());
                    smsMessage.setType(SmsTemplate.T005.getKey());
                    smsMessage.setPhone(order.get("userPhone").toString());
                    smsMessage.setParams("你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "元，今日为还款最后期限，请及时还款！");
                    rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
                });
            }
        } catch (Exception e) {
            logger.error("今日逾期短信发送异常", e);
        }
    }


    /**
     * 逾期提醒 每天中午12点15分
     */
    @Scheduled(cron = "0 15 12 * * ?")
    public void overdue() {
        try {
            List<Map<String, Object>> orderList = orderService.findByStatusAndOverdays(33, ">", 0);
            for (Map<String, Object> order : orderList) {
                ThreadPoolUtils.executor.execute(() -> {
                    Order orderInfo = orderService.selectByPrimaryKey(Long.valueOf(order.get("orderId").toString()));
                    QueueSmsMessage smsMessage = new QueueSmsMessage();
                    smsMessage.setClientAlias(order.get("merchant").toString());
                    smsMessage.setType(SmsTemplate.T005.getKey());
                    smsMessage.setPhone(order.get("userPhone").toString());
                    smsMessage.setParams("你于" + new DateTime(orderInfo.getCreateTime()).toString("MM月dd日HH:mm:ss") + " 借款" + orderInfo.getBorrowMoney() + "已逾期，避免信用损失请尽快还款！");
                    rabbitTemplate.convertAndSend(RabbitConst.queue_sms, smsMessage);
                });
            }
        } catch (Exception e) {
            logger.error("逾期短信发送异常", e);
        }
    }

}
