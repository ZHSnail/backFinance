package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

public class ModelVo extends PageEntity {
    private String key;
    private String name;
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
