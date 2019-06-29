package com.mod.loan.util.yeepay;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujianjian
 * @ date 2019/6/15 21:00
 */
public class YeePayApiRequest {

    //绑卡支付请求
    public static JSONObject cardPayRequest(String requestno, String identityid, String cardtop,
                                             String cardlast, String amount, String productname, String terminalno, boolean issms) throws Exception {
        String unibindcardpayUri = Config.getInstance().getValue("unibindcardpayUri");
        String merchantno = Config.getInstance().getValue("merchantno");

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantno", merchantno);
        map.put("requestno", requestno);
        map.put("issms", String.valueOf(issms));
        map.put("identityid", identityid);
        map.put("identitytype", "USER_ID");
        map.put("cardtop", cardtop);
        map.put("cardlast", cardlast);
        map.put("amount", amount);
        map.put("productname", productname);
        map.put("requesttime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        map.put("terminalno", terminalno); //协议支付： SQKKSCENEKJ010 代扣： SQKKSCENE10 商户需开通对应协议支付/代扣权限

        return YeepayUtil.yeepayYOP(map, unibindcardpayUri);
    }
}
