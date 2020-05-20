package com.zhsnail.finance.entity;

import java.math.BigDecimal;

public class ScaleSalary {
    private String id;

    private BigDecimal scaleAmount;

    private BigDecimal scaleStage;

    private BigDecimal scaleGrad;

    private String debitAccountId;

    private String creditAccountId;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getScaleAmount() {
        return scaleAmount;
    }

    public void setScaleAmount(BigDecimal scaleAmount) {
        this.scaleAmount = scaleAmount;
    }

    public BigDecimal getScaleStage() {
        return scaleStage;
    }

    public void setScaleStage(BigDecimal scaleStage) {
        this.scaleStage = scaleStage;
    }

    public BigDecimal getScaleGrad() {
        return scaleGrad;
    }

    public void setScaleGrad(BigDecimal scaleGrad) {
        this.scaleGrad = scaleGrad;
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