package com.zhsnail.finance.entity;

import java.util.List;

public class Permission {
    private String id;

    private String permName;

    private String parentId;

    private String permLevel;

    private String url;

    private String permOrder;

    private String icon;

    private String status;

    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getPermLevel() {
        return permLevel;
    }

    public void setPermLevel(String permLevel) {
        this.permLevel = permLevel == null ? null : permLevel.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermOrder() {
        return permOrder;
    }

    public void setPermOrder(String permOrder) {
        this.permOrder = permOrder == null ? null : permOrder.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}