package com.mod.loan.itf.sms.moxintong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.message.QueueSmsMessage;
import com.mod.loan.itf.sms.chuangrui.ChuangRuiSms;
import com.mod.loan.util.HttpUtils;
import com.mod.loan.util.MD5;
import com.mod.loan.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kk
 */
public class MoXinTongSms {
    private static final Logger log = LoggerFactory.getLogger(MoXinTongSms.class);

    private static String url = "http://api.uoleem.com.cn/sms/httpBatchSend";

    /**
     * 普通短信
     */
    public static boolean send(QueueSmsMessage smsMessage) throws Exception {
        String ts = TimeUtils.parseTime(new Date(), TimeUtils.dateformat5);

        Map<String, String> data = new HashMap<>(5);
        MoXinTongSmsSign sign = MoXinTongSmsSign.getSign(smsMessage.getClientAlias());
        data.put("username", sign.getAccesskey());
        data.put("pwd", MD5.toMD5(sign.getAccesskey() + sign.getSecret() + ts));
        data.put("mobile", smsMessage.getPhone());
        if (null != smsMessage.getParams()) {
            data.put("content", smsMessage.getParams());
        }
        data.put("ts", ts);
        String body = HttpUtils.doPost(url, data);
        JSONObject o = JSON.parseObject(body);
        if (0 == (int) o.get("code")) {
            return true;
        } else {
            log.error("短信发送失败,message={},respone={}", JSON.toJSON(smsMessage), body);
            return false;
        }
    }

    public static void main(String[] args) {
        QueueSmsMessage smsMessage = new QueueSmsMessage("jishidai", "2003", "18329032015", "嘻嘻1111111");
        try {
            System.out.println(MoXinTongSms.send(smsMessage));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
