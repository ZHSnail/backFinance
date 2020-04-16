package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.entity.User;
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
}
