package com.mod.loan.service.impl;


import com.mod.loan.common.exception.BizException;
import com.mod.loan.common.message.OrderRepayQueryMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.model.User;
import com.mod.loan.model.UserBank;
import com.mod.loan.service.OrderRepayService;
import com.mod.loan.service.UserBankService;
import com.mod.loan.service.UserService;
import com.mod.loan.service.YeePayService;
import com.mod.loan.util.yeepay.StringResultDTO;
import com.mod.loan.util.yeepay.YeePayApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ author liujianjian
 * @ date 2019/6/15 20:55
 */
@Slf4j
@Service
public class YeePayServiceImpl implements YeePayService {

    @Resource
    private UserService userService;
    @Resource
    private UserBankService userBankService;
    @Resource
    private OrderRepayService orderRepayService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    //还款
    @Override
    public void repay(Order order) {
        try {
            String timeStr = System.currentTimeMillis() + "";
            String requestno = order.getOrderNo() + timeStr.substring(timeStr.length() - 6);

            long uid = order.getUid();

            String identityid = ("dev".equals(Constant.ENVIROMENT) ? "YBTest" : "YB") + uid;

            UserBank userBank = userBankService.selectUserCurrentBankCard(order.getUid());
            if (userBank == null || StringUtils.isBlank(userBank.getCardNo())) {
                throw new BizException("用户未绑卡");
            }

            // 卡号前六位
            String cardtop = userBank.getCardNo().substring(0, 6);
            // 卡号后四位
            String cardlast = userBank.getCardNo().substring(userBank.getCardNo().length() - 4);
            // 还款金额
            String amount = order.getShouldRepay().toPlainString();
            if ("dev".equals(Constant.ENVIROMENT)) {
                amount = "0.01";
            }
            String productname = "还款";
            // 协议支付： SQKKSCENEKJ010 代扣： SQKKSCENE10 商户需开通对应协议支付/代扣权限
            String terminalno = "SQKKSCENEKJ010";

            StringResultDTO result = YeePayApiRequest.cardPayRequest(
                    requestno, identityid, cardtop, cardlast, amount, productname, terminalno, false);

            String status = result.getStatus();
            log.info("易宝返回值:{}", status);
            if ("PAY_FAIL".equalsIgnoreCase(status) || "FAIL".equalsIgnoreCase(status)) {
                log.error("易宝还款失败: " + status);
                return;
            }


            log.info("插入还款记录开始");
            OrderRepay orderRepay = new OrderRepay();
            orderRepay.setRepayNo(result.getYborderid());
            orderRepay.setUid(uid);
            orderRepay.setOrderId(order.getId());
            orderRepay.setRepayType(7);
            BigDecimal repayMoney = new BigDecimal(amount);
            orderRepay.setRepayMoney(repayMoney);
            orderRepay.setBank(userBank.getCardName());
            orderRepay.setBankNo(userBank.getCardNo());
            orderRepay.setCreateTime(new Date());
            orderRepay.setUpdateTime(new Date());
            orderRepay.setRepayStatus(0);
            orderRepayService.insertSelective(orderRepay);

            User user = userService.selectByPrimaryKey(order.getUid());
            OrderRepayQueryMessage message = new OrderRepayQueryMessage();
            message.setMerchantAlias(user.getMerchant());
            message.setRepayNo(result.getYborderid());
            message.setTimes(1);
            message.setRepayType(2);
            rabbitTemplate.convertAndSend(RabbitConst.yeepay_queue_repay_order_query, message);
            log.info("插入还款记录结束");
            return;
        } catch (Exception e) {
            log.error("易宝还款异常: " + e.getMessage(), e);
        }
    }

}
