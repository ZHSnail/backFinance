package com.zhsnail.finance.entity;

import java.math.BigDecimal;

public class AccountBalance {
    private String id;

    private BigDecimal debitStayearAmt;

    private BigDecimal creditStayearAmt;

    private BigDecimal creditStaperiodAmt;

    private BigDecimal debitStaperiodAmt;

    private BigDecimal creditEndperiodAmt;

    private BigDecimal debitEndperiodAmt;

    private BigDecimal creditCurrperiodAmt;

    private BigDecimal debitCurrperiodAmt;

    private BigDecimal creditAccumyearAmt;

    private BigDecimal debitAccumyearAmt;

    private String accountPeriod;

    private String accountId;

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