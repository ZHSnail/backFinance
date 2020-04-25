package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDetailVo extends PageEntity {
    private String id;

    //借方总金额
    private BigDecimal debitAmount;
//贷方总金额
    private BigDecimal creditAmount;
//会计期间
    private String accountPeriod;
//凭证id
    private String voucherId;
//会计科目id
    private String accountId;
    //贷方期初余额
    private BigDecimal creditStaperiodAmt;
    //借方期初余额
    private BigDecimal debitStaperiodAmt;
    //贷方期末余额
    private BigDecimal creditEndperiodAmt;
    //借方期末余额
    private BigDecimal debitEndperiodAmt;
    //记账日期、业务日期
    private Date bizDate;
    //业务单号
    private String bizCode;
    //过账日期
    private Date postingDate;
    //凭证号
    private Integer voucherCode;
    //摘要
    private String memo;
    //会计科目名
    private String accountName;
    //会计科目编码
    private String accountCode;
    //制单人
    private String originator;

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getCreditStaperiodAmt() {
        return creditStaperiodAmt;
    }

    public void setCreditStaperiodAmt(BigDecimal creditStaperiodAmt) {
        this.creditStaperiodAmt = creditStaperiodAmt;
    }

    public BigDecimal getDebitStaperiodAmt() {
        return debitStaperiodAmt;
    }

    public void setDebitStaperiodAmt(BigDecimal debitStaperiodAmt) {
        this.debitStaperiodAmt = debitStaperiodAmt;
    }

    public BigDecimal getCreditEndperiodAmt() {
        return creditEndperiodAmt;
    }

    public void setCreditEndperiodAmt(BigDecimal creditEndperiodAmt) {
        this.creditEndperiodAmt = creditEndperiodAmt;
    }

    public BigDecimal getDebitEndperiodAmt() {
        return debitEndperiodAmt;
    }

    public void setDebitEndperiodAmt(BigDecimal debitEndperiodAmt) {
        this.debitEndperiodAmt = debitEndperiodAmt;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Integer getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(Integer voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
