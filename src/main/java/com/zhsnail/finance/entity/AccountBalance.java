package com.zhsnail.finance.entity;

import java.math.BigDecimal;

//会计科目余额表
public class AccountBalance {
    private String id;
    //借方年初余额
    private BigDecimal debitStayearAmt;
    //贷方年初余额
    private BigDecimal creditStayearAmt;
    //贷方期初余额
    private BigDecimal creditStaperiodAmt;
//借方期初余额
    private BigDecimal debitStaperiodAmt;
//贷方期末余额
    private BigDecimal creditEndperiodAmt;
//借方期末余额
    private BigDecimal debitEndperiodAmt;
//贷方本期发生额
    private BigDecimal creditCurrperiodAmt;
//借方本期发生额
    private BigDecimal debitCurrperiodAmt;
//贷方本年累计发生额
    private BigDecimal creditAccumyearAmt;
//借方本年累计发生额
    private BigDecimal debitAccumyearAmt;
//会计期间
    private String accountPeriod;
//会计科目id
    private String accountId;

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getDebitStayearAmt() {
        return debitStayearAmt;
    }

    public void setDebitStayearAmt(BigDecimal debitStayearAmt) {
        this.debitStayearAmt = debitStayearAmt;
    }

    public BigDecimal getCreditStayearAmt() {
        return creditStayearAmt;
    }

    public void setCreditStayearAmt(BigDecimal creditStayearAmt) {
        this.creditStayearAmt = creditStayearAmt;
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

    public BigDecimal getCreditCurrperiodAmt() {
        return creditCurrperiodAmt;
    }

    public void setCreditCurrperiodAmt(BigDecimal creditCurrperiodAmt) {
        this.creditCurrperiodAmt = creditCurrperiodAmt;
    }

    public BigDecimal getDebitCurrperiodAmt() {
        return debitCurrperiodAmt;
    }

    public void setDebitCurrperiodAmt(BigDecimal debitCurrperiodAmt) {
        this.debitCurrperiodAmt = debitCurrperiodAmt;
    }

    public BigDecimal getCreditAccumyearAmt() {
        return creditAccumyearAmt;
    }

    public void setCreditAccumyearAmt(BigDecimal creditAccumyearAmt) {
        this.creditAccumyearAmt = creditAccumyearAmt;
    }

    public BigDecimal getDebitAccumyearAmt() {
        return debitAccumyearAmt;
    }

    public void setDebitAccumyearAmt(BigDecimal debitAccumyearAmt) {
        this.debitAccumyearAmt = debitAccumyearAmt;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod == null ? null : accountPeriod.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}