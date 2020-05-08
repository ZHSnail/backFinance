package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.PayNotice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PayDetailVo extends PageEntity {
    private String id;
    //备注
    private String memo;
    //付款金额
    private BigDecimal amount;
    //付款日期
    private Date payDate;
    //付款状态
    private String status;
    //收费方式
    private String feeMethod;
    //单号
    private String code;
    //用户id
    private String userId;
    //用户名
    private String userName;
    //用户idlist
    private List<String> userIdList;
    //缴费通知单id
    private String payNoticeId;
    private PayNotice payNotice;

    public PayNotice getPayNotice() {
        return payNotice;
    }

    public void setPayNotice(PayNotice payNotice) {
        this.payNotice = payNotice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayNoticeId() {
        return payNoticeId;
    }

    public void setPayNoticeId(String payNoticeId) {
        this.payNoticeId = payNoticeId;
    }

    private Date createTime;

    private String creater;

    private String updater;

    private Date updateTime;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeeMethod() {
        return feeMethod;
    }

    public void setFeeMethod(String feeMethod) {
        this.feeMethod = feeMethod;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
