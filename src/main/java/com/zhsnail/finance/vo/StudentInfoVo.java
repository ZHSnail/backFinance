package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.Profession;

public class StudentInfoVo extends PageEntity {
    private String id;
    //学生名
    private String name;
    //学号
    private String stuNo;
    //班级
    private String stuClass;
    //宿舍id
    private String dormId;
    //专业id
    private String professionId;
    //专业信息
    private DormInfo dormInfo;
    //宿舍信息
    private Profession profession;

    public DormInfo getDormInfo() {
        return dormInfo;
    }

    public void setDormInfo(DormInfo dormInfo) {
        this.dormInfo = dormInfo;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
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

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }
}
