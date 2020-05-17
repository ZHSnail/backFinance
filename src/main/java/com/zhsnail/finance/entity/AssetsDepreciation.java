package com.zhsnail.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AssetsDepreciation {
    private String id;

    private Date createTime;

    private String creater;

    private String code;

    private String status;

    private Date updateTime;

    private String updater;

    private String assetsId;
    //折旧金额
    private BigDecimal depreAmount;
    //折旧时间
    private Date depreTime;
    //折旧说明
    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
        this.creater = creater == null ? null : creater.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId == null ? null : assetsId.trim();
    }

    public BigDecimal getDepreAmount() {
        return depreAmount;
    }

    public void setDepreAmount(BigDecimal depreAmount) {
        this.depreAmount = depreAmount;
    }

    public Date getDepreTime() {
        return depreTime;
    }

    public void setDepreTime(Date depreTime) {
        this.depreTime = depreTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}