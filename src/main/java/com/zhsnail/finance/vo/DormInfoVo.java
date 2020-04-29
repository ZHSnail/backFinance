package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

public class DormInfoVo extends PageEntity {
    private String id;
    //宿舍号
    private String dormNumber;
    //楼号
    private String buildNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDormNumber() {
        return dormNumber;
    }

    public void setDormNumber(String dormNumber) {
        this.dormNumber = dormNumber;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }
}
