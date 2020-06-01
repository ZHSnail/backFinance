package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.Account;

import java.math.BigDecimal;

public class AccountTempVo {
    private String id;

    private String voucherId;

    private String accountId;

    private BigDecimal debitAmt;

    private BigDecimal creditAmt;
    //借贷方向
    private String direction;
    //会计科目
    private Account account;

    private String acconutName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAcconutName() {
        return acconutName;
    }

    public void setAcconutName(String acconutName) {
        this.acconutName = acconutName;
    }
}
