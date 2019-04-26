package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.message.RiskAuditMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.OrderPayMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderPay;
import com.mod.loan.service.OrderService;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderPayMapper orderPayMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void updatePayInfo(Order order, OrderPay orderPay) {
		if (order != null) {
			orderMapper.updateByPrimaryKeySelective(order);
		}
		orderPayMapper.insertSelective(orderPay);
	}

	@Override
	public void updatePayCallbackInfo(Order order, OrderPay orderPay) {
		orderMapper.updateByPrimaryKeySelective(order);
		orderPayMapper.updateByPrimaryKeySelective(orderPay);
	}

	@Override
	public void updateOverdueInfo() {
		int numOne = orderMapper.updateToOverdue();
		logger.info("今日逾期订单数为：{}", numOne);
		int numTwo = orderMapper.updateOverdueFee();
		logger.info("逾期费用更新完毕，影响行数为：{}", numTwo);
	}

	@Override
	public List<Map<String, Object>> findByStatusAndOverdays(Integer status, String op, Integer days) {
		return orderMapper.findByStatusAndOverdays(status, op, days);
	}

	@Override
	public void modifyOrderAuditAgain(int minute) {
		List<Order> list = orderMapper.findOrderWaitAutoAudit(minute);
		for (Order order : list) {
			rabbitTemplate.convertAndSend(RabbitConst.queue_risk_order_notify, new RiskAuditMessage(order.getId(), order.getMerchant(), 1, order.getUid(), null));
			orderMapper.updateOrderVersion(order.getId());
		}
	}

	@Override
	public int updateToBadDebt() {
		return orderMapper.updateToBadDebt();
	}

	@Override
	public List<Order> findByRepayTime(String repayTime) {
		return orderMapper.findByRepayTime(repayTime);
	}

}
