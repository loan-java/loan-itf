package com.mod.loan.service;

public interface ReportService {

	/**
	 * 根据日期重新统计一段时间内的 还款逾期情况
	 * 
	 * @param date
	 *            当前日期
	 * @param day
	 *            当前日期向前推进的天数
	 */
	void reportOrderRepay(String date, Integer day);

	/**
	 * 根据日期重新统计当日放款情况
	 * 
	 * @param date
	 *            当前日期
	 */
	void reportOrderLoan(String date);

	/**
	 * 统计渠道
	 * 
	 * @param date
	 *            当前日期
	 * @param day
	 *            当前日期向前推进的天数
	 * @param merchant
	 *            商户别名
	 */
	void reportPartnerEffect(String date, Integer day, String merchant);

	/**
	 * 注册提单
	 * 
	 * @param date
	 *            当前日期
	 */
	void reportRegisterOrder(String date);
}
