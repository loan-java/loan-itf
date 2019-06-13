package com.mod.loan.service;

import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.model.Order;
import com.mod.loan.model.User;

/**
 * @author kk
 */
public interface KuaiQianService {

    void repay(Order order);
}
