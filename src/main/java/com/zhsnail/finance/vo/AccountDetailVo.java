package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.Voucher;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDetailVo extends PageEntity {
    @ExcelIgnore
    private String id;
    //会计期间
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","会计期间"})
    private String accountPeriod;
    //会计科目名
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","会计科目名"})
    private String accountName;
    //会计科目编码
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","会计科目编码"})
    private String accountCode;
    //凭证号
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","凭证号"})
    private String voucherCode;
    //过账日期
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","过账日期"})
    private Date postingDate;
    //摘要
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","摘要"})
    private String memo;
    //制单人
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","制单人"})
    private String originator;
    //借方总金额
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","借方金额"})
    private BigDecimal debitAmount;
    //贷方总金额
    @ColumnWidth(20)
    @ExcelProperty({"会计科目明细账","贷方金额"})
    private BigDecimal creditAmount;
    //凭证id
    @ExcelIgnore
    private String voucherId;
    //会计科目id
    @ExcelIgnore
    private String accountId;
    //贷方期初余额
    @ExcelIgnore
    private BigDecimal creditStaperiodAmt;
    //借方期初余额
    @ExcelIgnore
    private BigDecimal debitStaperiodAmt;
    //贷方期末余额
    @ExcelIgnore
    private BigDecimal creditEndperiodAmt;
    //借方期末余额
    @ExcelIgnore
    private BigDecimal debitEndperiodAmt;
    //记账日期、业务日期
    @ExcelIgnore
    private Date bizDate;
    //业务单号
    @ExcelIgnore
    private String bizCode;
    @ExcelIgnore
    private Voucher voucher;
    @ExcelIgnore
    private AccountBalance accountBalance;

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(AccountBalance accountBalance) {
        this.accountBalance = accountBalance;
    }

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

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
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
