package com.zhsnail.finance.entity;

import java.util.Date;

public class ActivitiDeployment {
    private String id;

    private String name;

    private String category;

    private String tenantId;

    private Date deployTime;

    private ActivitiProcess activitiProcess;

    public ActivitiProcess getActivitiProcess() {
        return activitiProcess;
    }

    public void setActivitiProcess(ActivitiProcess activitiProcess) {
        this.activitiProcess = activitiProcess;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}