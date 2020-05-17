package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;

public class AssetsVo extends PageEntity {
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
    //备注
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetsKindId() {
        return assetsKindId;
    }

    public void setAssetsKindId(String assetsKindId) {
        this.assetsKindId = assetsKindId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        this.depreMethod = depreMethod;
    }

    public String getLossReport() {
        return lossReport;
    }

    public void setLossReport(String lossReport) {
        this.lossReport = lossReport;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
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
        this.num = num;
    }

    public String getObtainMethod() {
        return obtainMethod;
    }

    public void setObtainMethod(String obtainMethod) {
        this.obtainMethod = obtainMethod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}
