package com.mod.loan.common.message;

public class QueueVoiceMessage {


    /**
     * 客户端别名
     */
    private String clientAlias;

    /**
     * 语音事件
     * 10001.当日到期语音提示
     */
    private String type;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 参数值，多个参数值用"|"隔开
     */
    private String params;

    private String orderId;
    private String uid;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClientAlias() {
        return clientAlias;
    }

    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
