package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PayNotice {
    private String id;

    private BigDecimal totalAmount;

    private String accountId;

    private String org;

    private String memo;

    private String totalUser;

    private BigDecimal amount;

    private String feeScope;

    private String status;

    private String feeKindId;

    private String code;

    private String period;

    private Date deadLineMax;

    private Date deadLineMin;

    private String payDetailId;

    private Date createTime;

    private String creater;

    private String updater;

    private Date updateTime;

    private FeeKind feeKind;

    public FeeKind getFeeKind() {
        return feeKind;
    }

    public void setFeeKind(FeeKind feeKind) {
        this.feeKind = feeKind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(String totalUser) {
        this.totalUser = totalUser == null ? null : totalUser.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFeeScope() {
        return feeScope;
    }

    public void setFeeScope(String feeScope) {
        this.feeScope = feeScope == null ? null : feeScope.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFeeKindId() {
        return feeKindId;
    }

    public void setFeeKindId(String feeKindId) {
        this.feeKindId = feeKindId == null ? null : feeKindId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public Date getDeadLineMax() {
        return deadLineMax;
    }

    public void setDeadLineMax(Date deadLineMax) {
        this.deadLineMax = deadLineMax;
    }

    public Date getDeadLineMin() {
        return deadLineMin;
    }

    public void setDeadLineMin(Date deadLineMin) {
        this.deadLineMin = deadLineMin;
    }

    public String getPayDetailId() {
        return payDetailId;
    }

    public void setPayDetailId(String payDetailId) {
        this.payDetailId = payDetailId == null ? null : payDetailId.trim();
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
}