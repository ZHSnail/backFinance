package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

import java.util.Date;
import java.util.List;

public class SystemParamVo extends PageEntity {
    private String id;
    //当前会计期间
    private String nowAccountPeriod;
    //本位币
    private String baseCurrency;
    //启用状态
    private String state;
    //开始使用时间
    private Date startTime;
    //单位
    private String unit;
    //会计科目余额list
    private List<AccountBalanceVo> accountBalanceVoList;

    public List<AccountBalanceVo> getAccountBalanceVoList() {
        return accountBalanceVoList;
    }

    public void setAccountBalanceVoList(List<AccountBalanceVo> accountBalanceVoList) {
        this.accountBalanceVoList = accountBalanceVoList;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNowAccountPeriod() {
        return nowAccountPeriod;
    }

    public void setNowAccountPeriod(String nowAccountPeriod) {
        this.nowAccountPeriod = nowAccountPeriod;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
