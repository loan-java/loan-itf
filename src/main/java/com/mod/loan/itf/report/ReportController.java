package com.mod.loan.itf.report;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.service.ReportService;
import com.mod.loan.util.TimeUtils;

@RestController
@RequestMapping(value = "report")
public class ReportController {

	public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportService;

	/**
	 * 统计订单还款情况，默认统计明天之前
	 */
	@RequestMapping(value = "report_order_repay")
	public ResultMessage report_order_repay(String date, @RequestParam(defaultValue = "15") Integer day) {
		if (StringUtils.isBlank(date)) {
			date = new DateTime().plusDays(1).toString(TimeUtils.dateformat2);
		}
		logger.info("开始统计还款情况,日期={},day={}", date, day);
		reportService.reportOrderRepay(date, day);
		return new ResultMessage(ResponseEnum.M2000);
	}

	/**
	 * 统计订单放款款情况，默认统计当天数据
	 */
	@RequestMapping(value = "report_order_loan")
	public ResultMessage report_order_loan(String date) {
		if (StringUtils.isBlank(date)) {
			date = new DateTime().toString(TimeUtils.dateformat2);
		}
		logger.info("开始统计订单放款款情况,日期={}", date);
		reportService.reportOrderLoan(date);
		return new ResultMessage(ResponseEnum.M2000);
	}

}
