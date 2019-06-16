package com.mod.loan.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.exception.BizException;
import com.mod.loan.common.message.OrderRepayQueryMessage;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.model.User;
import com.mod.loan.model.UserBank;
import com.mod.loan.service.OrderRepayService;
import com.mod.loan.service.UserBankService;
import com.mod.loan.service.UserService;
import com.mod.loan.service.YeePayService;
import com.mod.loan.util.yeepay.YeePayApiRequest;
import lombok.extern.slf4j.Slf4j;
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

    //绑卡
    @Override
    public void requestBindCard(long uid, String orderNo, String cardno, String phone) throws Exception {

        User user = userService.selectByPrimaryKey(uid);
        if (user == null) throw new BizException("用户(" + uid + ")不存在");

        String identityid = "" + uid;
        String idcardno = user.getUserCertNo();
        String username = user.getUserName();

        YeePayApiRequest.bindCardRequest(orderNo, identityid, cardno, idcardno, username, phone);

    }

    //确认绑卡
    @Override
    public void confirmBindCard(String orderNo, String validateCode) throws Exception {
        JSONObject result = YeePayApiRequest.bindCardConfirm(orderNo, validateCode);
        String status = result.getString("status");
        if ("BIND_SUCCESS".equalsIgnoreCase(status)) return;

        throw new BizException("绑卡失败: " + status);
    }

    //还款
    @Override
    public ResultMessage repay(Order order) {
        try {
            String requestno = order.getOrderNo();

            long uid = order.getUid();

            String identityid = "" + uid;

            UserBank userBank = userBankService.selectUserCurrentBankCard(order.getUid());

            String cardtop = userBank.getCardNo().substring(0, 6); //卡号前六位
            String cardlast = userBank.getCardNo().substring(userBank.getCardNo().length() - 4); //卡号后四位

            String amount = order.getShouldRepay().toPlainString();
            String productname = "还款";

            String terminalno = "SQKKSCENEKJ010";  //协议支付： SQKKSCENEKJ010 代扣： SQKKSCENE10 商户需开通对应协议支付/代扣权限

            JSONObject result = YeePayApiRequest.cardPayRequest(requestno, identityid, cardtop, cardlast, amount, productname, terminalno, false);

            String status = result.getString("status");

            if ("PAY_FAIL".equalsIgnoreCase(status) || "FAIL".equalsIgnoreCase(status)) {
                return new ResultMessage(ResponseEnum.M4000.getCode(), "易宝还款失败: " + status);
            }

            String yborderid = result.getString("yborderid");

            OrderRepay orderRepay = new OrderRepay();
            orderRepay.setRepayNo(yborderid);
            orderRepay.setUid(uid);
            orderRepay.setOrderId(order.getId());
            orderRepay.setRepayType(1);
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
            message.setRepayNo(yborderid);
            message.setTimes(1);
            message.setRepayType(1);
            rabbitTemplate.convertAndSend(RabbitConst.yeepay_queue_repay_order_query, message);

            JSONObject object = new JSONObject();
            object.put("repayOrderNo", yborderid);
            object.put("shouldRepayAmount", amount);
            return new ResultMessage(ResponseEnum.M2000, object);
        } catch (Exception e) {
            log.error("易宝还款异常: " + e.getMessage(), e);
            return new ResultMessage(ResponseEnum.M4000.getCode(), "易宝还款失败: " + e.getMessage());
        }
    }

}
