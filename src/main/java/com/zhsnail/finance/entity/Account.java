package com.zhsnail.finance.entity;

public class Account {
    private String id;

    private String accountName;

    private String code;

    private String level;

    private String parentId;

    private String isCash;

    private String isBank;

    private String isDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsCash() {
        return isCash;
    }

    public void setIsCash(String isCash) {
        this.isCash = isCash == null ? null : isCash.trim();
    }

    public String getIsBank() {
        return isBank;
    }

    public void setIsBank(String isBank) {
        this.isBank = isBank == null ? null : isBank.trim();
    }

    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail == null ? null : isDetail.trim();
    }
}