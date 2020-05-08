package com.zhsnail.finance.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PayNoticeVo extends PageEntity {
    @ExcelIgnore
    private String id;
    //单号
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","单号"})
    private String code;
    //收费类别id
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","收费类别"})
    private String feeKindId;
    //创建人
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","发起人"})
    private String creater;
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","收费机构"})
    //收费机构
    private String org;
    //状态
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","状态"})
    private String status;
    //会计科目id
    @ExcelIgnore
    private String accountId;
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","借方会计科目"})
    private String debitAccount;
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","贷方会计科目"})
    private String creditAccount;
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","应收总金额"})
    //总金额
    private BigDecimal totalAmount;
    //总人数
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","待缴费总人数"})
    private String totalUser;
    //收费金额
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","收费金额"})
    private BigDecimal amount;
    //缴费期限
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","开始缴费日期"})
    private Date deadLineMin;
    //缴费期限
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","结束缴费日期"})
    private Date deadLineMax;
    //备注
    @ColumnWidth(20)
    @ExcelProperty({"缴费通知申请","备注"})
    private String memo;
    //收费范围
    @ExcelIgnore
    private List<Map> feeScope;
    //收费单位
    @ExcelIgnore
    private String period;
    //收费单详情
    @ExcelIgnore
    private String payDetailId;
    //创建时间
    @ExcelIgnore
    private Date createTime;
    //更新人
    @ExcelIgnore
    private String updater;
    //更新时间
    @ExcelIgnore
    private Date updateTime;
    @ExcelIgnore
    private Map feeKindList;

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Map getFeeKindList() {
        return feeKindList;
    }

    public void setFeeKindList(Map feeKindList) {
        this.feeKindList = feeKindList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(String totalUser) {
        this.totalUser = totalUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Map> getFeeScope() {
        return feeScope;
    }

    public void setFeeScope(List<Map> feeScope) {
        this.feeScope = feeScope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeeKindId() {
        return feeKindId;
    }

    public void setFeeKindId(String feeKindId) {
        this.feeKindId = feeKindId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDeadLineMax() {
        return deadLineMax;
    }

    public void setDeadLineMax(Date deadLineMax) {
        this.deadLineMax = deadLineMax;
    }

    public Date getDeadLineMin() {
        return deadLineMin;
    }

    public void setDeadLineMin(Date deadLineMin) {
        this.deadLineMin = deadLineMin;
    }

    public String getPayDetailId() {
        return payDetailId;
    }

    public void setPayDetailId(String payDetailId) {
        this.payDetailId = payDetailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
