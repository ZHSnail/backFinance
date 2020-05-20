package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.ScaleSalary;
import com.zhsnail.finance.entity.StationSalary;


public class StationInfoVo extends PageEntity {
    @ExcelIgnore
    private String id;
    //岗位类型
    @ExcelIgnore
    private String type;
    //岗位名称
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","岗位名称"})
    private String name;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","岗位类型"})
    private String typeName;
    //状态
    @ExcelIgnore
    private String state;
    @ExcelIgnore
    private String stationSalaryId;
    @ExcelIgnore
    private String scaleSalaryId;
    //岗位工资
    @ExcelIgnore
    private StationSalary stationSalary;
    //薪级工资
    @ExcelIgnore
    private ScaleSalary scaleSalary;
    @ExcelIgnore
    private String debitScaleAccId;
    @ExcelIgnore
    private String creditScaleAccId;
    @ExcelIgnore
    private String debitStationAccId;
    @ExcelIgnore
    private String creditStationAccId;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","借方薪级工资出处科目"})
    private String debitScaleAccName;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","贷方薪级工资出处科目"})
    private String creditScaleAccName;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","借方岗位工资出处科目"})
    private String debitStationAccName;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","贷方岗位工资出处科目"})
    private String creditStationAccName;
    @ColumnWidth(20)
    @ExcelProperty({"岗位信息","状态"})
    private String stateName;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDebitScaleAccName() {
        return debitScaleAccName;
    }

    public void setDebitScaleAccName(String debitScaleAccName) {
        this.debitScaleAccName = debitScaleAccName;
    }

    public String getCreditScaleAccName() {
        return creditScaleAccName;
    }

    public void setCreditScaleAccName(String creditScaleAccName) {
        this.creditScaleAccName = creditScaleAccName;
    }

    public String getDebitStationAccName() {
        return debitStationAccName;
    }

    public void setDebitStationAccName(String debitStationAccName) {
        this.debitStationAccName = debitStationAccName;
    }

    public String getCreditStationAccName() {
        return creditStationAccName;
    }

    public void setCreditStationAccName(String creditStationAccName) {
        this.creditStationAccName = creditStationAccName;
    }

    public String getDebitScaleAccId() {
        return debitScaleAccId;
    }

    public void setDebitScaleAccId(String debitScaleAccId) {
        this.debitScaleAccId = debitScaleAccId;
    }

    public String getCreditScaleAccId() {
        return creditScaleAccId;
    }

    public void setCreditScaleAccId(String creditScaleAccId) {
        this.creditScaleAccId = creditScaleAccId;
    }

    public String getDebitStationAccId() {
        return debitStationAccId;
    }

    public void setDebitStationAccId(String debitStationAccId) {
        this.debitStationAccId = debitStationAccId;
    }

    public String getCreditStationAccId() {
        return creditStationAccId;
    }

    public void setCreditStationAccId(String creditStationAccId) {
        this.creditStationAccId = creditStationAccId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStationSalaryId() {
        return stationSalaryId;
    }

    public void setStationSalaryId(String stationSalaryId) {
        this.stationSalaryId = stationSalaryId;
    }

    public String getScaleSalaryId() {
        return scaleSalaryId;
    }

    public void setScaleSalaryId(String scaleSalaryId) {
        this.scaleSalaryId = scaleSalaryId;
    }

    public StationSalary getStationSalary() {
        return stationSalary;
    }

    public void setStationSalary(StationSalary stationSalary) {
        this.stationSalary = stationSalary;
    }

    public ScaleSalary getScaleSalary() {
        return scaleSalary;
    }

    public void setScaleSalary(ScaleSalary scaleSalary) {
        this.scaleSalary = scaleSalary;
    }
}
