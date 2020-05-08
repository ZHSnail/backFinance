package com.zhsnail.finance.entity;

public class FeeKind {
    private String id;
    //费用名称
    private String name;
    //时间单位
    private String timeMold;
    //收费方式
    private String feeMethod;
    //状态
    private String state;
    //借方科目id
    private String debitAccountId;
    //贷方科目id
    private String creditAccountId;
    //借方科目
    private Account debitAccount;
    //贷方科目
    private Account creditAccount;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTimeMold() {
        return timeMold;
    }

    public void setTimeMold(String timeMold) {
        this.timeMold = timeMold == null ? null : timeMold.trim();
    }

    public String getFeeMethod() {
        return feeMethod;
    }

    public void setFeeMethod(String feeMethod) {
        this.feeMethod = feeMethod == null ? null : feeMethod.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId) {
        this.debitAccountId = debitAccountId == null ? null : debitAccountId.trim();
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId) {
        this.creditAccountId = creditAccountId == null ? null : creditAccountId.trim();
    }
}