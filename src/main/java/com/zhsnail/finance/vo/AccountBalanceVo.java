package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;

public class AccountBalanceVo extends PageEntity {
    @ExcelIgnore
    private String id;
    //会计期间
    @ExcelProperty({"会计科目余额表","会计期间"})
    private String accountPeriod;
    @ExcelProperty({"会计科目余额表","会计科目名称"})
    private String accountName;
    @ExcelProperty({"会计科目余额表","会计科目编码"})
    private String accountCode;
    //借方年初余额
    @ExcelProperty({"会计科目余额表","借方年初余额"})
    private BigDecimal debitStayearAmt;
    //贷方年初余额
    @ExcelProperty({"会计科目余额表","贷方年初余额"})
    private BigDecimal creditStayearAmt;
    //借方期初余额
    @ExcelProperty({"会计科目余额表","借方期初余额"})
    private BigDecimal debitStaperiodAmt;
    //贷方期初余额
    @ExcelProperty({"会计科目余额表","贷方期初余额"})
    private BigDecimal creditStaperiodAmt;
    //借方期末余额
    @ExcelProperty({"会计科目余额表","借方期末余额"})
    private BigDecimal debitEndperiodAmt;
    //贷方期末余额
    @ExcelProperty({"会计科目余额表","贷方期末余额"})
    private BigDecimal creditEndperiodAmt;
    //借方本期发生额
    @ExcelProperty({"会计科目余额表","借方本期发生额"})
    private BigDecimal debitCurrperiodAmt;
    //贷方本期发生额
    @ExcelProperty({"会计科目余额表","贷方本期发生额"})
    private BigDecimal creditCurrperiodAmt;
    //借方本年累计发生额
    @ExcelProperty({"会计科目余额表","借方本年累计发生额"})
    private BigDecimal debitAccumyearAmt;
    //贷方本年累计发生额
    @ExcelProperty({"会计科目余额表","贷方本年累计发生额"})
    private BigDecimal creditAccumyearAmt;
    //会计科目id
    @ExcelIgnore
    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.accountPeriod = accountPeriod;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
