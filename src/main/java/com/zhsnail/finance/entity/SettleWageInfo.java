package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SettleWageInfo {
    private String id;
    //执行期间
    private Date exeDate;
    //名称
    private String name;
    //薪酬期间
    private String salaryPeriod;
    //总金额
    private BigDecimal totalAmount;
    //关联人数
    private BigDecimal relatedNumber;
    //成功人数
    private BigDecimal successNumber;
    //工资单id
    private String payStubInfoId;

    private List<StaffInfo> staffInfoList;

    private PayStubInfo payStubInfo;

    public List<StaffInfo> getStaffInfoList() {
        return staffInfoList;
    }

    public void setStaffInfoList(List<StaffInfo> staffInfoList) {
        this.staffInfoList = staffInfoList;
    }

    public PayStubInfo getPayStubInfo() {
        return payStubInfo;
    }

    public void setPayStubInfo(PayStubInfo payStubInfo) {
        this.payStubInfo = payStubInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSalaryPeriod() {
        return salaryPeriod;
    }

    public void setSalaryPeriod(String salaryPeriod) {
        this.salaryPeriod = salaryPeriod == null ? null : salaryPeriod.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRelatedNumber() {
        return relatedNumber;
    }

    public void setRelatedNumber(BigDecimal relatedNumber) {
        this.relatedNumber = relatedNumber;
    }

    public BigDecimal getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(BigDecimal successNumber) {
        this.successNumber = successNumber;
    }

    public String getPayStubInfoId() {
        return payStubInfoId;
    }

    public void setPayStubInfoId(String payStubInfoId) {
        this.payStubInfoId = payStubInfoId == null ? null : payStubInfoId.trim();
    }
}