package com.mod.loan.task;

import com.mod.loan.service.OrderUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * loan-itf 2019/6/3 huijin.shuailijie Init
 */
@Component
public class QjldOrderTask {

    public static final Logger logger = LoggerFactory.getLogger(OrderTask.class);

    @Autowired
    private OrderUserService orderUserService;

    /**
     * 每10分钟重新提取未进入风控的订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void getQjldQueryOrder() {
        try {
            orderUserService.getQjldQueryOrder();
        } catch (Exception e) {
            logger.error("重新提取未进入风控的订单异常", e);
        }
    }
}
