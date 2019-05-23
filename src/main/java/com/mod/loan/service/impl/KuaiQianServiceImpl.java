package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.ResponseEnum;
import com.mod.loan.common.message.OrderRepayQueryMessage;
import com.mod.loan.common.model.ResultMessage;
import com.mod.loan.config.Constant;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;
import com.mod.loan.model.User;
import com.mod.loan.model.UserBank;
import com.mod.loan.service.KuaiQianService;
import com.mod.loan.service.OrderRepayService;
import com.mod.loan.service.UserBankService;
import com.mod.loan.util.StringUtil;
import com.mod.loan.util.TimeUtils;
import com.mod.loan.util.kuaiqian.Post;
import com.mod.loan.util.kuaiqian.entity.TransInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * @author kk
 */
@Service
public class KuaiQianServiceImpl implements KuaiQianService {
    private static Logger logger = LoggerFactory.getLogger(KuaiQianServiceImpl.class);

    @Autowired
    private UserBankService userBankService;
    @Autowired
    private OrderRepayService orderRepayService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 还款
     */
    @Override
    public void repay(Order order, long uid, User user) {
        UserBank userBank = userBankService.selectUserCurrentBankCard(uid);

        TransInfo transInfo = new TransInfo();
        /* 消费信息 */
        //版本号
        String version = Constant.kuaiQianVersion;
        //交易类型
        String txnType = "PUR";
        //特殊交易标志
        String spFlag = "QPay02";
        //消息状态
        String interactiveStatus = "TR1";
        //支付标记payToken
        String payToken = userBank.getForeignId();
        //交易金额,以元为单位，小数点后最多两位
        String amount = order.getShouldRepay().toPlainString();
        //商户编号
        String merchantId = Constant.kuaiQianMemberId;
        //终端编号
        String terminalId = Constant.kuaiQianTerminalId;
        //商户端交易时间
        String entryTime = TimeUtils.parseTime(new Date(), TimeUtils.dateformat5);
        //外部跟踪号
        String externalRefNumber = StringUtil.getOrderNumber("rp");
        //客户号
        String customerId = Constant.KUAI_QIAN_UID_PFX + uid;
        //tr3回调地址
        String tr3Url = "";

        //设置消费交易的两个节点
        transInfo.setRecordeText_1("TxnMsgContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        StringBuilder str1Xml = new StringBuilder();

        str1Xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        str1Xml.append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">");
        str1Xml.append("<version>").append(version).append("</version>");

        str1Xml.append("<TxnMsgContent>");
        str1Xml.append("<interactiveStatus>").append(interactiveStatus).append("</interactiveStatus>");
        str1Xml.append("<spFlag>").append(spFlag).append("</spFlag>");
        str1Xml.append("<txnType>").append(txnType).append("</txnType>");
        str1Xml.append("<merchantId>").append(merchantId).append("</merchantId>");
        str1Xml.append("<terminalId>").append(terminalId).append("</terminalId>");
        str1Xml.append("<externalRefNumber>").append(externalRefNumber).append("</externalRefNumber>");
        str1Xml.append("<entryTime>").append(entryTime).append("</entryTime>");
        str1Xml.append("<amount>").append(amount).append("</amount>");
        str1Xml.append("<customerId>").append(customerId).append("</customerId>");
        str1Xml.append("<payToken>").append(payToken).append("</payToken>");
        str1Xml.append("<tr3Url>").append(tr3Url).append("</tr3Url>");

        str1Xml.append("<extMap>");
        str1Xml.append("<extDate><key>phone</key><value></value></extDate>");
        str1Xml.append("<extDate><key>validCode</key><value></value></extDate>");
        str1Xml.append("<extDate><key>savePciFlag</key><value>0</value></extDate>");
        str1Xml.append("<extDate><key>token</key><value></value></extDate>");
        str1Xml.append("<extDate><key>payBatch</key><value>2</value></extDate>");
        str1Xml.append("</extMap>");

        str1Xml.append("</TxnMsgContent>");
        str1Xml.append("</MasMessage>");

        //https://mas.99bill.com/cnp/purchase     //生产环境地址

        //TR2接收的数据
        HashMap respXml;
        try {
            respXml = Post.sendPost(Constant.kuaiQianRepayUrl, str1Xml.toString(), transInfo);
        } catch (Exception e) {
            logger.error("快钱协议支付接口请求异常，param={}， e", str1Xml.toString(), e);
            return;
        }
        logger.info("快钱协议支付接口返回参数，respXml = {}", respXml);

        if (respXml == null) {
            logger.error("快钱协议支付接口，读取内容出错，请求参数={}", str1Xml.toString());
            return;
        } else {
            //如果TR2获取的应答码responseCode的值为00时，成功
            if ("00".equals(respXml.get("responseCode"))
                    || "C0".equals(respXml.get("responseCode"))
                    || "68".equals(respXml.get("responseCode"))) {
                /* 进行数据库的逻辑操作，比如更新数据库或插入记录。 */
                logger.info("快钱协议支付接口, 调用成功, 交易成功, orderNo={}, repayNo={}", order.getOrderNo(), externalRefNumber);
                OrderRepay orderRepay = new OrderRepay();
                orderRepay.setRepayNo(externalRefNumber);
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

                OrderRepayQueryMessage message = new OrderRepayQueryMessage();
                message.setMerchantAlias(user.getMerchant());
                message.setRepayNo(externalRefNumber);
                message.setTimes(1);
                message.setRepayType(2);
                rabbitTemplate.convertAndSend(RabbitConst.kuaiqian_queue_repay_order_query, message);
                return;
            }
        }

        logger.error("快钱还款失败，请求参数为={},响应参数为={}", str1Xml.toString(), JSON.toJSONString(respXml));
    }
}
