package com.mod.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.mapper.OrderAuditMapper;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.model.OrderAudit;
import com.mod.loan.service.OrderAuditService;

@Service
public class OrderAuditServiceImpl extends BaseServiceImpl<OrderAudit,Long>  implements OrderAuditService {

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderAuditMapper orderAuditMapper;

}
