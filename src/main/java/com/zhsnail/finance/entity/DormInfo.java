package com.zhsnail.finance.entity;

public class DormInfo {
    private String id;
    //宿舍号
    private String dormNumber;
    //楼号
    private String buildNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDormNumber() {
        return dormNumber;
    }

    public void setDormNumber(String dormNumber) {
        this.dormNumber = dormNumber == null ? null : dormNumber.trim();
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber == null ? null : buildNumber.trim();
    }
}