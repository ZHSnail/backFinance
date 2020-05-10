package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zhsnail.finance.common.CustomStringStringConverter;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.PageEntity;

import java.util.List;


public class AccountVo extends PageEntity {
    @ExcelIgnore
    private String id;
    @ExcelProperty({"会计科目表","科目名称"})
    private String accountName;
    @ExcelProperty({"会计科目表","科目编码"})
    private String code;
    @ExcelProperty(value = {"会计科目表","会计科目类型"},converter = CustomStringStringConverter.class)
    private String type;
    @ExcelProperty({"会计科目表","级次"})
    private String level;
    @ExcelIgnore
    private String parentId;
    @ExcelProperty({"会计科目表","父级科目名称"})
    private String parentName;
    @ExcelProperty(value = {"会计科目表","是否现金"},converter = CustomStringStringConverter.class)
    private String isCash;
    @ExcelProperty(value = {"会计科目表","是否银行"},converter = CustomStringStringConverter.class)
    private String isBank;
    @ExcelProperty(value = {"会计科目表","是否明细"},converter = CustomStringStringConverter.class)
    private String isDetail;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail;
    }
}
