package com.mod.loan.task;

import com.mod.loan.mapper.UserMapper;
import com.mod.loan.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTask {

    public static final Logger logger = LoggerFactory.getLogger(OrderTask.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 凌晨1点05分计算利息
     */
    @Scheduled(cron = "0 05 1 * * ?")
    public void updateInterestFee() {
        try {
            orderService.updateInterestFee();
        } catch (Exception e) {
            logger.error("更新利息费用计算定时异常", e);
        }
    }

    /**
     * 凌晨1点10分执行计算总费用
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void updateOverdueInfoTask() {
        try {
            orderService.updateOverdueInfo();
        } catch (Exception e) {
            logger.error("更新订单逾期状态及费用计算定时异常", e);
        }
    }

    /**
     * 每天15点执行
     */
    @Scheduled(cron = "0 0 15 * * ?")
    public void updateToBadDebtTask() {
        try {
            orderService.updateToBadDebt();
        } catch (Exception e) {
            logger.error("更新订单为坏账时异常", e);
        }
    }

    /**
     * 找出待机审中，等待超过10分钟的订单重新风控审核
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void sendOrder2RiskAgain() {
        try {
            orderService.modifyOrderAuditAgain(30);
        } catch (Exception e) {
            logger.error("等待超过30分钟的订单重新风控审核异常", e);
        }
    }
}
