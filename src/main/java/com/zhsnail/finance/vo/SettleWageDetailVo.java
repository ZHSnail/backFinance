package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.StaffInfo;

import java.math.BigDecimal;

public class SettleWageDetailVo extends PageEntity {
    private String id;
    //工资单结算id
    private String settleWageId;
    //员工id
    private String staffId;
    //应发金额
    private BigDecimal payableAmount;
    //应纳税额
    private BigDecimal taxableAmount;
    //实发金额
    private BigDecimal paidAmount;
    //扣费金额
    private BigDecimal deductionAmount;

    private StaffInfo staffInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSettleWageId() {
        return settleWageId;
    }

    public void setSettleWageId(String settleWageId) {
        this.settleWageId = settleWageId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(BigDecimal taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public StaffInfo getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(StaffInfo staffInfo) {
        this.staffInfo = staffInfo;
    }
}
