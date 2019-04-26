package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Order;

public interface OrderMapper extends MyBaseMapper<Order> {

	/**
	 * 更新为逾期状态
	 * 
	 * @return
	 */
	int updateToOverdue();

	/**
	 * 更新逾期费用
	 * 
	 * @return
	 */
	int updateOverdueFee();

	/**
	 * 更新利息费用
	 * @return
	 */
	int updateInterestFee();

	/**
	 * 查询逾期或即将逾期的订单
	 * 
	 * @param status
	 * @param op
	 * @param days
	 * @return
	 */
	List<Map<String, Object>> findByStatusAndOverdays(@Param("status") Integer status, @Param("op") String op, @Param("days") Integer days);

	/**
	 * 更新为坏账状态
	 * 
	 * @return
	 */
	int updateToBadDebt();

	/**
	 * 等待时间
	 * 
	 * @param minute
	 * @return
	 */
	List<Order> findOrderWaitAutoAudit(Integer minute);

	/**
	 * 更新订单版本号
	 * 
	 * @param orderId
	 * @return
	 */
	int updateOrderVersion(Long orderId);

	/**
	 * 找当天逾期的订单
	 * @return
	 */
	List<Order> findOverdueOrders();

	/**
	 * 找当天坏账的订单
	 * @return
	 */
	List<Order> findBadOrders();

}