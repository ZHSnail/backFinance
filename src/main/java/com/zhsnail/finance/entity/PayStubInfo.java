package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PayStubInfo {
    private String id;
    //名称
    private String name;
    //使用范围
    private String scope;

    private List<FloatWage> floatWageList;
    //最新执行期间
    private Date lastExeDate;
    //关联人数
    private BigDecimal relatedNumber;

    public List<FloatWage> getFloatWageList() {
        return floatWageList;
    }

    public void setFloatWageList(List<FloatWage> floatWageList) {
        this.floatWageList = floatWageList;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public Date getLastExeDate() {
        return lastExeDate;
    }

    public void setLastExeDate(Date lastExeDate) {
        this.lastExeDate = lastExeDate;
    }

    public BigDecimal getRelatedNumber() {
        return relatedNumber;
    }

    public void setRelatedNumber(BigDecimal relatedNumber) {
        this.relatedNumber = relatedNumber;
    }
}