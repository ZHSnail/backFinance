package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;

public class VoucherVo extends PageEntity {
    //凭证号
    private Integer code;

    private String id;
    //业务单id
    private String bizId;
    //模块名
    private String module;
    //过账状态
    private String postingStatus;
    //状态
    private String status;
    //制单人
    private String originator;
    //审核人
    private String auditer;
    //过账人
    private String keeper;
    //过账日期
    private Date postingDate;
    //业务类型
    private String bizType;
    //借方总金额
    private BigDecimal debitTotal;
    //会计期间
    private String accountPeriod;
    //记账日期、业务日期
    private Date bizDate;
    //交易类型
    private String dealType;
    //贷方总金额
    private BigDecimal creditTotal;
    //备注
    private String memo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public BigDecimal getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(BigDecimal debitTotal) {
        this.debitTotal = debitTotal;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public BigDecimal getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(BigDecimal creditTotal) {
        this.creditTotal = creditTotal;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
