package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PayStubInfoVo extends PageEntity {
    private String id;
    //名称
    private String name;
    //使用范围
    private String scope;

    private List<FloatWage> floatWageList;

    private List<String> deductList;

    private List<String> shouldPaidList;

    private List<String> unitPayList;

    private String deduct;

    private String shouldPaid;

    private String unitPay;

    private String scopeName;
    //最新执行期间
    private Date lastExeDate;
    //关联人数
    private BigDecimal relatedNumber;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<FloatWage> getFloatWageList() {
        return floatWageList;
    }

    public void setFloatWageList(List<FloatWage> floatWageList) {
        this.floatWageList = floatWageList;
    }

    public List<String> getDeductList() {
        return deductList;
    }

    public void setDeductList(List<String> deductList) {
        this.deductList = deductList;
    }

    public List<String> getShouldPaidList() {
        return shouldPaidList;
    }

    public void setShouldPaidList(List<String> shouldPaidList) {
        this.shouldPaidList = shouldPaidList;
    }

    public List<String> getUnitPayList() {
        return unitPayList;
    }

    public void setUnitPayList(List<String> unitPayList) {
        this.unitPayList = unitPayList;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }

    public String getShouldPaid() {
        return shouldPaid;
    }

    public void setShouldPaid(String shouldPaid) {
        this.shouldPaid = shouldPaid;
    }

    public String getUnitPay() {
        return unitPay;
    }

    public void setUnitPay(String unitPay) {
        this.unitPay = unitPay;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
