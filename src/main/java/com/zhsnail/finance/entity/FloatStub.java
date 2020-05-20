package com.zhsnail.finance.entity;

public class FloatStub {
    private String id;

    private String payStubInfoId;

    private String floatWageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPayStubInfoId() {
        return payStubInfoId;
    }

    public void setPayStubInfoId(String payStubInfoId) {
        this.payStubInfoId = payStubInfoId == null ? null : payStubInfoId.trim();
    }

    public String getFloatWageId() {
        return floatWageId;
    }

    public void setFloatWageId(String floatWageId) {
        this.floatWageId = floatWageId == null ? null : floatWageId.trim();
    }
}