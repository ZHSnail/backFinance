package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zhsnail.finance.common.CustomStringStringConverter;

public class AccountData {

    private String accountName;

    private String code;

    private String level;

    private String parentCode;
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String isDetail;
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String isCash;
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String isBank;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail;
    }

    public String getIsCash() {
        return isCash;
    }

    public void setIsCash(String isCash) {
        this.isCash = isCash;
    }

    public String getIsBank() {
        return isBank;
    }

    public void setIsBank(String isBank) {
        this.isBank = isBank;
    }
}
