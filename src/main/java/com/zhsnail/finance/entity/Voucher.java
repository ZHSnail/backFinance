package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 凭证类
 */
public class Voucher {
    private String id;
    //凭证号
    private String code;
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
    //原单号
    private String bizCode;
    //会计科目分录
    private List<AccountTemp> accountTempList;

    public List<AccountTemp> getAccountTempList() {
        return accountTempList;
    }

    public void setAccountTempList(List<AccountTemp> accountTempList) {
        this.accountTempList = accountTempList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus == null ? null : postingStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer == null ? null : auditer.trim();
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper == null ? null : keeper.trim();
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
        this.bizType = bizType == null ? null : bizType.trim();
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
        this.accountPeriod = accountPeriod == null ? null : accountPeriod.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
        this.dealType = dealType == null ? null : dealType.trim();
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
        this.memo = memo == null ? null : memo.trim();
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode == null ? null : bizCode.trim();
    }
}