package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PayDetail {
    private String id;
    //备注
    private String memo;
    //付款金额
    private BigDecimal amount;
    //付款日期
    private Date payDate;
    //付款状态
    private String status;
    //收费方式
    private String feeMethod;
    //单号
    private String code;
    //用户id
    private String userId;
    //缴费通知单id
    private String payNoticeId;

    private Date createTime;

    private String creater;

    private String updater;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFeeMethod() {
        return feeMethod;
    }

    public void setFeeMethod(String feeMethod) {
        this.feeMethod = feeMethod == null ? null : feeMethod.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayNoticeId() {
        return payNoticeId;
    }

    public void setPayNoticeId(String payNoticeId) {
        this.payNoticeId = payNoticeId == null ? null : payNoticeId.trim();
    }
}