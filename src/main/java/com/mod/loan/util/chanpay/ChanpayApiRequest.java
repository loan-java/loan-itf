package com.mod.loan.util.chanpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.exception.BizException;
import com.mod.loan.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ author liujianjian
 * @ date 2019/6/18 20:41
 */
@Slf4j
@Component
public class ChanpayApiRequest {

    private static ChanpayGateway chanpay = new ChanpayGateway();

    //协议支付请求
    public CardPayResponse cardPayRequest(String orderNo, String userId, String cardPre, String cardSuf, String amount) throws Exception {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = chanpay.setCommonMap(origMap);
        origMap.put("Service", "nmg_biz_api_quick_payment");// 支付的接口名
        // 2.2 业务参数
        origMap.put("TrxId", orderNo);// 订单号
        origMap.put("OrdrName", "还款");// 商品名称
        origMap.put("MerUserId", userId);// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", Constant.chanpayMerchantNo);// 子账户号
//        origMap.put("SubMerchantNo", "");// 子商户号
        origMap.put("ExpiredTime", "40m");// 订单有效期
        origMap.put("CardBegin", cardPre);// 卡号前6位
        origMap.put("CardEnd", cardSuf);// 卡号后4位
        origMap.put("TrxAmt", amount);// 交易金额，元
        origMap.put("TradeType", "11");// 交易类型，11-即时，12-担保
        origMap.put("SmsFlag", "0");
        JSONObject json = doPost(origMap);
        return new CardPayResponse(json.getString("OrderTrxid"));
    }

    private JSONObject doPost(Map<String, String> origMap) throws Exception {
        log.info("畅捷 api 请求开始, params: " + JSON.toJSONString(origMap));
        String result = chanpay.gatewayPost(origMap);
        log.info("畅捷 api 请求结束, result: " + result);

        JSONObject json = JSON.parseObject(result);
        String acceptStatus = json.getString("AcceptStatus");
        String appRetMsg = json.getString("AppRetMsg");
        String appRetCode = json.getString("AppRetcode");
        String status = json.getString("Status");
        String retCode = json.getString("RetCode");
        String retMsg = json.getString("RetMsg");

        if ("F".equals(acceptStatus) || "F".equals(status)) {
            throw new BizException(StringUtils.isNotBlank(retCode) ? retCode : appRetCode, StringUtils.isNotBlank(retMsg) ? retMsg : appRetMsg);
        }
        return json;
    }

    public static class CardPayResponse {
        private String orderTrxid; //支付时畅捷订单号

        public CardPayResponse(String orderTrxid) {
            this.orderTrxid = orderTrxid;
        }

        public String getOrderTrxid() {
            return orderTrxid;
        }

        public void setOrderTrxid(String orderTrxid) {
            this.orderTrxid = orderTrxid;
        }
    }

}
