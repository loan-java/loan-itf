package com.mod.loan.task;

import com.mod.loan.mapper.UserIdentMapper;
import com.mod.loan.util.TimeUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserTask {

	public static final Logger logger = LoggerFactory.getLogger(UserTask.class);
	@Autowired
	UserIdentMapper userIdentMapper;

//	@Scheduled(cron = "0/5 * * * * ?")
	@Scheduled(cron = "0 1 0 * * ?") //凌晨0点1分执行
	public void updateUserIdentExpire() {
		try {
			String mobile_date = new DateTime().minusDays(20).toString(TimeUtils.dateformat1);
			int num = userIdentMapper.expireUserIdentMobile(mobile_date);
			logger.info("运营商认证失效数={}",num);
		} catch (Exception e) {
			logger.error("运营商认证失效异常", e);
		}
		try {
			String alipay_date = new DateTime().minusDays(60).toString(TimeUtils.dateformat1);
			int num = userIdentMapper.expireUserIdentZfb(alipay_date);
			logger.info("支付宝认证失效数={}",num);
		} catch (Exception e) {
			logger.error("支付宝认证失效异常", e);
		}
	}

}
