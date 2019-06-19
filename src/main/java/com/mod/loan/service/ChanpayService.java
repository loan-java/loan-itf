package com.mod.loan.service;

import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Order;

public interface ChanpayService {

    /**
     * 还款
     *
     * @param order
     * @return
     */
    ResultMessage repay(Order order);
}
