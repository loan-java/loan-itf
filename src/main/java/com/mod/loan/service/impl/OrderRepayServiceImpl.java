package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.OrderRepayMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.model.User;
import com.mod.loan.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderRepayServiceImpl extends BaseServiceImpl<OrderRepay, String> implements OrderRepayService {
    private static Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private OrderRepayMapper orderRepayMapper;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBankService userBankService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private KuaiQianService kuaiQianService;


    @Autowired
    private BaofooService baofooService;
    @Resource
    private YeePayService yeePayService;
    @Resource
    private ChanpayService chanpayService;

    @Override
    public void updateOrderRepayInfo(OrderRepay orderRepay, Order order) {
        orderRepayMapper.updateByPrimaryKeySelective(orderRepay);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int countRepaySuccess(Long orderId) {
        return orderRepayMapper.countRepaySuccess(orderId);
    }

    @Override
    public void repay(Order order) throws Exception {
        User user = userService.selectByPrimaryKey(order.getUid());
        Merchant merchant = merchantService.selectByPrimaryKey(user.getMerchant());
        logger.info("逾期订单id: {}, bindType={}", order.getId(), merchant.getBindType());

        switch (merchant.getBindType()) {
            case 4:
                baofooService.repay(order);
                break;
            case 5:
                kuaiQianService.repay(order);
                break;
            case 6:
                chanpayService.repay(order);
                break;
            case 7:
                logger.info("bindType={}", 7);
                yeePayService.repay(order);
                break;
            default:
                throw new Exception("支付渠道异常");
        }
    }

}
