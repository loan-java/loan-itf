package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_moxie_zfb_fail")
public class MoxieZfbFail {
    @Id
    private Long id;

    /**
     * 魔蝎唯一标识
     */
    @Column(name = "task_id")
    private String taskId;

    private Long uid;

    /**
     * task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    private String status;

    /**
     * 错误描述
     */
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取魔蝎唯一标识
     *
     * @return task_id - 魔蝎唯一标识
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置魔蝎唯一标识
     *
     * @param taskId 魔蝎唯一标识
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

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
     * 获取task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     *
     * @return status - task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     *
     * @param status task.submit-任务创建通知,task-任务授权登录结果通知,task.fail-任务采集失败通知,bill-账单通知, report-用户报告通知
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取错误描述
     *
     * @return remark - 错误描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置错误描述
     *
     * @param remark 错误描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}