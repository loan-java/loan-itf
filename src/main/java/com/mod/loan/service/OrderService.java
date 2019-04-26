package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderPay;
import com.mod.loan.model.User;

public interface OrderService extends BaseService<Order, Long> {

	void updatePayInfo(Order order, OrderPay orderPay);

	void updatePayCallbackInfo(Order order, OrderPay orderPay);

	void updateOverdueInfo();

	List<Map<String, Object>> findByStatusAndOverdays(Integer status, String op, Integer days);

	/**
	 * 向风控发送重新 等待时间过长的待机审核订单
	 * 
	 * @param minute
	 */
	void modifyOrderAuditAgain(int minute);

	int updateToBadDebt();

	void orderCallBack(User user, String orderNo, Integer orderStatus);

	List<Order> findOverdueOrders();

	List<Order> findBadOrders();

}
