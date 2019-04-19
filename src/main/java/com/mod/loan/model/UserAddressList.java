package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_user_address_list")
public class UserAddressList {
	@Id
    private Long uid;

    /**
     * 通讯录唯一标识
     */
    @Column(name = "task_id")
    private String taskId;

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 通讯录信息，吴光宇^1586712,135|马翔^qsqsqs,sqws
     */
    @Column(name = "address_list")
    private String addressList;

    @Column(name = "update_time")
    private Date updateTime;
    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList == null ? null : addressList.trim();
    }

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}