package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;

/**
 * @author kk
 */
public interface OrderRepayService extends BaseService<OrderRepay, String> {

    void updateOrderRepayInfo(OrderRepay orderRepay, Order order);

    int countRepaySuccess(Long orderId);

    void repay(Order order);
}
