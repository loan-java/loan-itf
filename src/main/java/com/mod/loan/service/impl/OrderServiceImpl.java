package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.OrderStatusEnum;
import com.mod.loan.common.enums.OrderTypeEnum;
import com.mod.loan.common.enums.PayStatusEnum;
import com.mod.loan.common.enums.RepayStatusEnum;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.message.RiskAuditMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.OrderPayMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderPay;
import com.mod.loan.model.User;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.juhe.CallBackJuHeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private CallBackRongZeService callBackRongZeService;

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

        //逾期推送融泽订单状态
        List<Order> list = orderMapper.selectOverdueOrderRZ();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(order -> {
                if (order.getSource() == 1) {
                    callBackRongZeService.pushOrderStatus(order);
                }
            });
        }
    }

    @Override
    public void updateInterestFee() {
        int interestFee = orderMapper.updateInterestFee();
        logger.info("利息费用更新完毕，影响行数为：{}", interestFee);
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
    public List<Order> findByRepayTime(String repayTime) {
        return orderMapper.findByRepayTime(repayTime);
    }

    @Override
    public int updateToBadDebt() {
        return orderMapper.updateToBadDebt();
    }

    @Override
    public void orderCallBack(User user, String orderNo, Integer orderStatus) {

        JSONObject object = JSONObject.parseObject(user.getCommonInfo());
        object.put("orderNo", orderNo);
        object.put("orderType", OrderTypeEnum.JK.getCode());
        switch (orderStatus) {
            case 33:
                object.put("orderStatus", OrderStatusEnum.OVERDUE.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 34:
                object.put("orderStatus", OrderStatusEnum.BAD_DEBT.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            default:
                break;
        }

        CallBackJuHeUtil.callBack("", object);
    }

    @Override
    public List<Order> findOverdueOrders() {
        return orderMapper.findOverdueOrders();
    }

    @Override
    public List<Order> findBadOrders() {
        return orderMapper.findBadOrders();
    }

}
