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
    //会计科目id
    private String accountId;
    //会计科目
    private Account account;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
