package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.ImportResult;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.mapper.ImportResultMapper;
import com.zhsnail.finance.mapper.RoleMapper;
import com.zhsnail.finance.mapper.UserMapper;
import com.zhsnail.finance.util.CodeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ImportResultMapper importResultMapper;

    @Override
    public User findUserByStaId(String staffId) {
        return userMapper.findByStaId(staffId);
    }

    @Override
    public User findUserByStuId(String studentId) {
        return userMapper.findUserByStuId(studentId);
    }

    @Override
    public User findUser(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    public void saveUser(User user) {
        user.setId(CodeUtil.getId());
        userMapper.insert(user);
    }

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findRoleList(String userId) {
        User user = userMapper.findUserRoleById(userId);
        return user.getRoles();
    }

    @Override
    public User findUserInfo(String userName) {
        return userMapper.findUserInfo(userName);
    }

    @Override
    public ImportResult findImResult(String fileId) {
        return importResultMapper.findByfileId(fileId);
    }

    @Override
    public void saveImresult(ImportResult importResult) {
        importResultMapper.insert(importResult);
    }

    @Override
    public PageInfo<Role> findAllRole(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),true);
        List<Role> roleList = roleMapper.findAllRole();
        PageInfo<Role> rolePageInfo = new PageInfo<>(roleList);
        return rolePageInfo;
    }

    @Override
    public PageInfo<User> findAllUser(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),true);
        List<User> allUser = userMapper.findAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(allUser);
        return userPageInfo;
    }
}
