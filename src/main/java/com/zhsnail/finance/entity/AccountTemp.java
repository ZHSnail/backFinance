package com.zhsnail.finance.entity;

import java.math.BigDecimal;

/**
 * 会计科目冻结表，发生业务时会插到这个表里，过账后才直接算金额
 */
public class AccountTemp {
    private String id;

    private String voucherId;

    private String accountId;

    private BigDecimal debitAmt;

    private BigDecimal creditAmt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId == null ? null : voucherId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public BigDecimal getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(BigDecimal debitAmt) {
        this.debitAmt = debitAmt;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }
}