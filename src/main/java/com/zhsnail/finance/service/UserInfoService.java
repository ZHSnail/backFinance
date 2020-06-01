package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.Role;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    List<Map> findAllUserInfo();

    List<Role> getAllRoleList();
}
