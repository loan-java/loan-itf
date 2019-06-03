package com.mod.loan.service.impl;

import com.mod.loan.common.message.RiskAuditMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.mapper.OrderUserMapper;
import com.mod.loan.model.OrderUser;
import com.mod.loan.model.User;
import com.mod.loan.service.OrderUserService;
import com.mod.loan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * loan-itf 2019/6/3 huijin.shuailijie Init
 */
@Slf4j
@Service
public class OrderUserServiceImpl implements OrderUserService {

    @Autowired
    private OrderUserMapper orderUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void getQjldQueryOrder() {
        List<OrderUser> list = orderUserMapper.getQjldQueryOrder(1);
        if (list.size() > 0) {
            list.stream().forEach(orderUser -> {
                User user = userService.selectByPrimaryKey(orderUser.getUid());
                RiskAuditMessage message = new RiskAuditMessage();
                message.setOrderNo(orderUser.getOrderNo());
                message.setStatus(1);
                message.setMerchant(user.getMerchant());
                message.setUid(orderUser.getUid());
                message.setSource(orderUser.getSource());
                message.setTimes(0);
                try {
                    rabbitTemplate.convertAndSend(RabbitConst.queue_risk_order_notify, message);
                } catch (Exception e) {
                    log.error("消息发送异常：", e);
                }
            });
        }
    }
}
