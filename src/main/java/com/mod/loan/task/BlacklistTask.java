package com.mod.loan.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mod.loan.mapper.BlacklistMapper;

@Component
public class BlacklistTask {

    public static final Logger logger = LoggerFactory.getLogger(BlacklistTask.class);

    @Autowired
    BlacklistMapper blacklistMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void blacklistTask() {
        try {
            logger.info("黑名单失效定时开始");
            blacklistMapper.blacklistTask();
        } catch (Exception e) {
            logger.error("黑名单失效定时异常", e);
        }
    }

}
