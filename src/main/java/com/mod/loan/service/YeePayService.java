package com.mod.loan.service;

import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Order;

/**
 * @ author liujianjian
 * @ date 2019/6/15 20:41
 */
public interface YeePayService {
    //还款
    ResultMessage repay(Order order);
}
