package com.mod.loan.task;

import com.mod.loan.model.Order;
import com.mod.loan.service.OrderRepayService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 还款定时任务
 *
 * @author kk
 */
@Component
public class RepayTask {
    public static final Logger logger = LoggerFactory.getLogger(RepayTask.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepayService orderRepayService;

    /**
     * 自动扣款定时任务每天11点18点执行两次
     */
    //@Scheduled(cron = "0 0 11,18 * * ?")
    @Scheduled(cron = "5 0/2 * * * ? ")
    public void updateOverdueInfoTask() {
        try {
            logger.info("=====自动扣款定时任务 开始=====");
            String repayTime = TimeUtils.parseTime(new Date(), TimeUtils.dateformat2);
            List<Order> list = orderService.findByRepayTime(repayTime);
            for (Order order : list) {
                orderRepayService.repay(order);
            }
            logger.info("=====自动扣款定时任务 结束=====");
        } catch (Exception e) {
            logger.error("自动扣款定时任务异常", e);
        }
    }
}
