package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Assets {
    private String id;
    //资产类别id
    private String assetsKindId;
    //资产名称
    private String name;
    //资产编码
    private String code;
    //入库时间
    private Date storageTime;
    //折旧方法
    private String depreMethod;
    //是否报损
    private String lossReport;
    //预计使用年限
    private String usefulLife;
    //入库地点
    private String storagePlace;
    //规格
    private String norms;
    //原价值
    //即入库金额
    private BigDecimal orival;
    //残值率
    private BigDecimal salvage;
    //报损日期
    private Date lossReportTime;
    //清理费用
    private BigDecimal cleanCost;
    //数量
    private String num;
    //取得方式
    private String obtainMethod;
    //是否启用
    private String state;
    //采购订单id
    private String purchaseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAssetsKindId() {
        return assetsKindId;
    }

    public void setAssetsKindId(String assetsKindId) {
        this.assetsKindId = assetsKindId == null ? null : assetsKindId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getDepreMethod() {
        return depreMethod;
    }

    public void setDepreMethod(String depreMethod) {
        this.depreMethod = depreMethod == null ? null : depreMethod.trim();
    }

    public String getLossReport() {
        return lossReport;
    }

    public void setLossReport(String lossReport) {
        this.lossReport = lossReport == null ? null : lossReport.trim();
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife == null ? null : usefulLife.trim();
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace == null ? null : storagePlace.trim();
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms == null ? null : norms.trim();
    }

    public BigDecimal getOrival() {
        return orival;
    }

    public void setOrival(BigDecimal orival) {
        this.orival = orival;
    }

    public BigDecimal getSalvage() {
        return salvage;
    }

    public void setSalvage(BigDecimal salvage) {
        this.salvage = salvage;
    }

    public Date getLossReportTime() {
        return lossReportTime;
    }

    public void setLossReportTime(Date lossReportTime) {
        this.lossReportTime = lossReportTime;
    }

    public BigDecimal getCleanCost() {
        return cleanCost;
    }

    public void setCleanCost(BigDecimal cleanCost) {
        this.cleanCost = cleanCost;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getObtainMethod() {
        return obtainMethod;
    }

    public void setObtainMethod(String obtainMethod) {
        this.obtainMethod = obtainMethod == null ? null : obtainMethod.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId == null ? null : purchaseId.trim();
    }
}