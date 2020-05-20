package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StaffInfo {
    private String id;
    //职工号
    private String staffNumber;
    //职工姓名
    private String name;
    //学历
    private String degree;
    //证件号码
    private String cardNumber;
    //入职日期
    private Date entryDate;
    //所属部门
    private String orgId;
    //手机号码
    private String mobile;
    //电子邮箱
    private String mail;
    //所属岗位
    private String stationInfoId;
    //职称
    private String postTitle;
    //系统账号
    private String userName;
    //工资单id
    private String payStubInfoId;
    //岗位等级
    private String stationLv;
    //薪级等级
    private String scaleLv;
    //基础工资速算
    private BigDecimal postWageAmount;

    private StationInfo stationInfo;

    private PayStubInfo payStubInfo;

    private List<BankInfo> bankInfoList;

    private List<Role> roles;

    private String settleWageId;

    private OrgInfo orgInfo;

    public OrgInfo getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(OrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }

    public StationInfo getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(StationInfo stationInfo) {
        this.stationInfo = stationInfo;
    }

    public PayStubInfo getPayStubInfo() {
        return payStubInfo;
    }

    public void setPayStubInfo(PayStubInfo payStubInfo) {
        this.payStubInfo = payStubInfo;
    }

    public List<BankInfo> getBankInfoList() {
        return bankInfoList;
    }

    public void setBankInfoList(List<BankInfo> bankInfoList) {
        this.bankInfoList = bankInfoList;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getStationInfoId() {
        return stationInfoId;
    }

    public void setStationInfoId(String stationInfoId) {
        this.stationInfoId = stationInfoId == null ? null : stationInfoId.trim();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPayStubInfoId() {
        return payStubInfoId;
    }

    public void setPayStubInfoId(String payStubInfoId) {
        this.payStubInfoId = payStubInfoId == null ? null : payStubInfoId.trim();
    }

    public String getStationLv() {
        return stationLv;
    }

    public void setStationLv(String stationLv) {
        this.stationLv = stationLv == null ? null : stationLv.trim();
    }

    public String getScaleLv() {
        return scaleLv;
    }

    public void setScaleLv(String scaleLv) {
        this.scaleLv = scaleLv == null ? null : scaleLv.trim();
    }

    public BigDecimal getPostWageAmount() {
        return postWageAmount;
    }

    public void setPostWageAmount(BigDecimal postWageAmount) {
        this.postWageAmount = postWageAmount;
    }

    public String getSettleWageId() {
        return settleWageId;
    }

    public void setSettleWageId(String settleWageId) {
        this.settleWageId = settleWageId == null ? null : settleWageId.trim();
    }
}