package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.PageEntity;

import java.util.Date;

public class AssetsRegisterVo extends PageEntity {
    private String id;

    private Date createTime;

    private String creater;

    private String code;

    private String status;

    private Date updateTime;

    private String updater;

    private String assetsId;

    private String memo;

    private Assets assets;

    private AssetsVo assetsVo;

    public AssetsVo getAssetsVo() {
        return assetsVo;
    }

    public void setAssetsVo(AssetsVo assetsVo) {
        this.assetsVo = assetsVo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.updater = updater;
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}
