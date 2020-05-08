package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.PageEntity;

public class FeeKindVo extends PageEntity {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeMold() {
        return timeMold;
    }

    public void setTimeMold(String timeMold) {
        this.timeMold = timeMold;
    }

    public String getFeeMethod() {
        return feeMethod;
    }

    public void setFeeMethod(String feeMethod) {
        this.feeMethod = feeMethod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
