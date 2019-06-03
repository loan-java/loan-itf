package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.OrderStatusEnum;
import com.mod.loan.common.enums.OrderTypeEnum;
import com.mod.loan.common.enums.PayStatusEnum;
import com.mod.loan.common.enums.RepayStatusEnum;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.message.RiskAuditMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.Order;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private CallBackRongZeService callBackRongZeService;

    @Autowired
    private UserMapper userMapper;

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
        //推送流量端
        Order order = new Order();
        order.setStatus(33);
        List<Order> orderList = orderMapper.select(order);
        logger.info("开始回调逾期数据");
        int count = 0;
        for (Order order1 : orderList) {
            orderCallBack(userMapper.selectByPrimaryKey(order1.getUid()), order1);
            count++;
        }
        logger.info("回调逾期完毕,影响行数为：{}", count);
    }

    @Override
    public void updateInterestFee() {
        int interestFee = orderMapper.updateInterestFee();
        logger.info("利息费用更新完毕，影响行数为：{}", interestFee);
        List<Order> orderList = orderMapper.selectOrderList();
        logger.info("开始回调利息数据");
        int count = 0;
        for (Order order : orderList) {
            orderCallBack(userMapper.selectByPrimaryKey(order.getUid()), order);
            count++;
        }
        logger.info("回调利息完毕,影响行数为：{}", count);
    }

    @Override
    public List<Map<String, Object>> findByStatusAndOverdays(Integer status, String op, Integer days) {
        return orderMapper.findByStatusAndOverdays(status, op, days);
    }

    @Override
    public void modifyOrderAuditAgain(int minute) {
        List<Order> list = orderMapper.findOrderWaitAutoAudit(minute);
        for (Order order : list) {
            RiskAuditMessage message = new RiskAuditMessage();
            message.setOrderNo(order.getOrderNo());
            message.setStatus(1);
            message.setMerchant(order.getMerchant());
            message.setUid(order.getUid());
            message.setSource(order.getSource());
            message.setTimes(0);
            rabbitTemplate.convertAndSend(RabbitConst.qjld_queue_risk_order_notify, message);
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
    public void orderCallBack(User user, Order order) {

        JSONObject object = JSONObject.parseObject(user.getCommonInfo());
        object.put("orderNo", order.getOrderNo());
        object.put("orderType", OrderTypeEnum.JK.getCode());
        object.put("shouldRepayAmount", new BigDecimal(order.getShouldRepay().toString()).stripTrailingZeros().toPlainString());
        object.put("accountId", order.getUid());
        switch (order.getStatus()) {
            case 21:
                object.put("orderStatus", OrderStatusEnum.WAIT_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 22:
                object.put("orderStatus", OrderStatusEnum.TO_PAY.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 23:
                object.put("orderStatus", OrderStatusEnum.PAY_FAILED.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
            case 31:
                object.put("orderStatus", OrderStatusEnum.PAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYING.getCode());
                break;
            case 33:
                object.put("orderStatus", OrderStatusEnum.OVERDUE.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 34:
                object.put("orderStatus", OrderStatusEnum.BAD_DEBT.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 41:
                object.put("orderStatus", OrderStatusEnum.REPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.REPAYED.getCode());
                break;
            case 42:
                object.put("orderStatus", OrderStatusEnum.OVERDUEREPAYED.getCode());
                object.put("payStatus", PayStatusEnum.PAYED.getCode());
                object.put("repayStatus", RepayStatusEnum.OVERDUE_REPAY.getCode());
                break;
            case 51:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 52:
                object.put("orderStatus", OrderStatusEnum.AUDIT_REFUSE.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            case 53:
                object.put("orderStatus", OrderStatusEnum.CANCEL.getCode());
                object.put("payStatus", PayStatusEnum.NOTPAY.getCode());
                object.put("repayStatus", RepayStatusEnum.NOT_REPAY.getCode());
                break;
            default:
                break;
        }

        CallBackJuHeUtil.callBack(Constant.juheCallBackUrl, object);
    }

}
