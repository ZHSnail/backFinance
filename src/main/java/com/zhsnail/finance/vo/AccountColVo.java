package com.zhsnail.finance.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AccountColVo {
    //会计期间
    private String accountPeriod;
    //会计科目名
    private String accountName;
    //会计科目编码
    private String accountCode;
    //过账日期
    private Date postingDate;
    //凭证号
    private Integer voucherCode;
    //制单人
    private String originator;
    //摘要
    private String memo;
    //借方总金额
    private BigDecimal debitAmount;
    //贷方总金额
    private BigDecimal creditAmount;

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
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

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
}
