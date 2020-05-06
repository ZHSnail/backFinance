package com.zhsnail.finance.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PayNoticeVo {
    private String id;
    //总金额
    private BigDecimal totalAmount;
    //会计科目id
    private String accountId;
    //收费机构
    private String org;
    //备注
    private String memo;
    //总人数
    private String totalUser;
    //收费金额
    private BigDecimal amount;
    //收费范围
    private List<Map> feeScope;
    //状态
    private String status;
    //收费类别id
    private String feeKindId;
    //单号
    private String code;
    //收费单位
    private String period;
    //缴费期限
    private Date deadLineMax;
    //缴费期限
    private Date deadLineMin;
    //收费单详情
    private String payDetailId;
    //创建时间
    private Date createTime;
    //创建人
    private String creater;
    //更新人
    private String updater;
    //更新时间
    private Date updateTime;

    private Map feeKind;

    public Map getFeeKind() {
        return feeKind;
    }

    public void setFeeKind(Map feeKind) {
        this.feeKind = feeKind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.accountId = accountId;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(String totalUser) {
        this.totalUser = totalUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Map> getFeeScope() {
        return feeScope;
    }

    public void setFeeScope(List<Map> feeScope) {
        this.feeScope = feeScope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeeKindId() {
        return feeKindId;
    }

    public void setFeeKindId(String feeKindId) {
        this.feeKindId = feeKindId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
        this.payDetailId = payDetailId;
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
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
