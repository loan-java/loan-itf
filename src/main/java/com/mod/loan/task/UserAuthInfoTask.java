package com.mod.loan.task;

import com.mod.loan.service.UserAuthInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * loan-itf 2019/6/3 huijin.shuailijie Init
 */
@Component
public class UserAuthInfoTask {

    public static final Logger logger = LoggerFactory.getLogger(UserAuthInfoTask.class);

    @Autowired
    private UserAuthInfoService userAuthInfoService;

    /**
     * 每10分钟补全活体认证图片信息
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void getUserAuthInfo() {
        try {
            userAuthInfoService.getNeedCompletingUserAuthInfo();
        } catch (Exception e) {
            logger.error("重新补全活体认证图片信息异常", e);
        }
    }
}
