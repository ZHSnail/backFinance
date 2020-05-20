package com.zhsnail.finance.entity;

import java.math.BigDecimal;

public class StationSalary {
    private String id;

    private BigDecimal stationAmount;

    private BigDecimal stationStage;

    private BigDecimal stationGrad;

    private String debitAccountId;

    private String creditAccountId;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getStationAmount() {
        return stationAmount;
    }

    public void setStationAmount(BigDecimal stationAmount) {
        this.stationAmount = stationAmount;
    }

    public BigDecimal getStationStage() {
        return stationStage;
    }

    public void setStationStage(BigDecimal stationStage) {
        this.stationStage = stationStage;
    }

    public BigDecimal getStationGrad() {
        return stationGrad;
    }

    public void setStationGrad(BigDecimal stationGrad) {
        this.stationGrad = stationGrad;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId) {
        this.debitAccountId = debitAccountId == null ? null : debitAccountId.trim();
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId) {
        this.creditAccountId = creditAccountId == null ? null : creditAccountId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}