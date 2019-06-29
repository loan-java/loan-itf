package com.mod.loan.task;

import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mod.loan.model.Merchant;
import com.mod.loan.service.MerchantService;

@Component
public class ReportTask {

	public static final Logger logger = LoggerFactory.getLogger(ReportTask.class);

	@Value("${server.itf.url:}")
	String server_itf_url;
	@Autowired
	private MerchantService merchantService;
	

	@Scheduled(cron = "0 25 0/1 * * ?")
	public void report_order_loan() {
		try {
			Response execute = Jsoup.connect(server_itf_url + "report/report_order_loan?").ignoreContentType(true).ignoreHttpErrors(true).execute();
			logger.info("放款统计调用结果={}", execute.body());
		} catch (Exception e) {
			logger.error("放款统计异常", e);
		}
	}

	/**
	 * 还款统计在逾期之后
	 */
	@Scheduled(cron = "0 30 0/1 * * ?")
	public void report_order_repay() {
		try {
			Response execute = Jsoup.connect(server_itf_url + "report/report_order_repay?").ignoreContentType(true).ignoreHttpErrors(true).timeout(5000).execute();
			logger.info("还款统计调用结果={}", execute.body());
		} catch (Exception e) {
			logger.error("还款统计异常", e);
		}
	}
}
