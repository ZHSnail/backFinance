package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

public class ProfessionVo extends PageEntity {
    private String id;
    //专业名
    private String name;
    //是否叶子节点
    private String isLeaf;
    //父节点id
    private String parentId;
    //年级
    private String grade;

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

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
