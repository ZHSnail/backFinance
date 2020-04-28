package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

public class RoleVo extends PageEntity {
    private String id;

    private String roleName;

    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
