package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.RoleMapper;
import com.zhsnail.finance.mapper.RoleUserMapper;
import com.zhsnail.finance.mapper.StaffInfoMapper;
import com.zhsnail.finance.mapper.StudentInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.vo.StaffInfoVo;
import com.zhsnail.finance.vo.StudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private StaffInfoMapper staffInfoMapper;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public List<Map> findAllUserInfo() {
        List<StaffInfo> staffInfoList = staffInfoMapper.findAllByCondition(new StaffInfoVo());
        List<StudentInfo> StudentInfoList = studentInfoMapper.findAllByCondition(new StudentInfoVo());
        List<Map> list = new ArrayList<>();
        list.addAll(BeanUtil.objectsToMaps(staffInfoList));
        list.addAll(BeanUtil.objectsToMaps(StudentInfoList));
        return list;
    }

    @Override
    public List<Role> getAllRoleList() {
        return roleMapper.findAllRole();
    }
}
