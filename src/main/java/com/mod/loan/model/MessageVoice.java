package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_message_voice")
public class MessageVoice {
    @Id
    private Long id;

    private Long uid;

    @Column(name = "order_id")
    private Long orderId;

    private String remark;

    @Column(name = "call_id")
    private String callId;

    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 0:初始化；1:受理成功；2：回调接通；3：回调未接通；4：受理失败
     */
    private Integer status;

    /**
     * 1：腾讯云；2：联信
     */
    private Integer type;

    private String merchant;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取0:初始化；1:受理成功；2：回调接通；3：回调未接通；4：受理失败
     *
     * @return uid - 0:初始化；1:受理成功；2：回调接通；3：回调未接通；4：受理失败
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置0:初始化；1:受理成功；2：回调接通；3：回调未接通；4：受理失败
     *
     * @param uid 0:初始化；1:受理成功；2：回调接通；3：回调未接通；4：受理失败
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取通知结果
     *
     * @return status - 通知结果
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置通知结果
     *
     * @param status 通知结果
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     * @return create_time

     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}