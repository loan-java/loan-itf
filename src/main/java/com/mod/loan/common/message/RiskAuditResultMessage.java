package com.mod.loan.common.message;

public class RiskAuditResultMessage {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 1-通过，可放款 2-人审 3-拒绝
     */
    private Integer status;
    /**
     * 风控描述
     */
    private String remark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

/*    @Override
    public String toString() {
        return "RiskAuditResultMessage{" +
                "orderId='" + orderId + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }*/
}