package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;

public class FloatWageVo extends PageEntity {
    @ExcelIgnore
    private String id;
    //编码
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","编码"})
    private String code;
    //名称
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","名称"})
    private String name;
    //金额
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","金额"})
    private BigDecimal amount;
    @ExcelIgnore
    //计税类型
    private String taxType;
    @ExcelIgnore
    //扣减分类
    private String signType;
    @ExcelIgnore
    private String state;
    @ExcelIgnore
    private String debitAccountId;
    @ExcelIgnore
    private String creditAccountId;
    @ExcelIgnore
    private Account debitAccount;
    @ExcelIgnore
    private Account creditAccount;
    //计税类型
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","计税类型"})
    private String taxTypeName;
    //扣减分类
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","扣减分类"})
    private String signTypeName;
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","借方工资出处科目"})
    private String debitAccountName;
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","贷方工资出处科目"})
    private String creditAccountName;
    @ColumnWidth(20)
    @ExcelProperty({"浮动工资项","状态"})
    private String stateName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId) {
        this.creditAccountId = creditAccountId;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccountName() {
        return debitAccountName;
    }

    public void setDebitAccountName(String debitAccountName) {
        this.debitAccountName = debitAccountName;
    }

    public String getCreditAccountName() {
        return creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public String getSignTypeName() {
        return signTypeName;
    }

    public void setSignTypeName(String signTypeName) {
        this.signTypeName = signTypeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
