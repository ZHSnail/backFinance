package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.vo.RoleVo;
import com.zhsnail.finance.vo.SystemParamVo;

import java.util.List;
import java.util.Map;

public interface SystemService {
    /**
     * 根据员工id查找用户
     * @param staffId 员工id
     * @return User实体
     */
    User findUserByStaId(String staffId);

    /**
     * 根据学生id查找用户
     * @param studentId 学生id
     * @return User实体
     */
    User findUserByStuId(String studentId);

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return User实体
     */
    User findUser(String userName);

    /**
     * 保存User实体
     * @param user user
     */
    void saveUser(User user);

    /**
     * 根据id查询用户
     * @param id id
     * @return User实体
     */
    User findUserById(String id);

   /* *//**
     * 根据用户id查找角色
     * @param userId 用户id
     * @return List<Role>
     *//*
    List<Role> findRoleList(String userId);*/

    /**
     * 查询用户的权限及菜单
     * @param userName 用户名
     * @return user实体
     */
    User findUserInfo(String userName);

    /**
     * 查询导入结果
     * @param fileId 文件id
     * @return
     */
    ImportResult findImResult(String fileId);

    /**
     * 保存导入结果
     * @param importResult 导入结果实体
     */
    void saveImresult(ImportResult importResult);

    /**
     * 分页查询所有角色
     * @param roleVo 角色vo
     * @return
     */
    PageInfo<Role> findAllRole(RoleVo roleVo);

    /**
     * 分页查询所有用户
     * @param pageEntity 页面实体
     * @return
     */
    PageInfo<User> findAllUser(PageEntity pageEntity);

    /**
     * 删除角色
     * @param id 角色id
     */
    void deleteRole(String id);

    /**
     * 修改角色
     * @param roleVo 角色
     */
    void updateRole(RoleVo roleVo);

    /**
     * 保存角色
     * @param roleVo
     */
    void saveRole(RoleVo roleVo);

    /**
     * 根据id查找学生信息
     * @param id
     * @return
     */
    StudentInfo findStudentInfoById(String id);

    /**
     * 保存系统参数
     * @param systemParamVo 系统参数
     */
    void saveSystemParam(SystemParamVo systemParamVo);

    /**
     * 获取当前启用的系统参数
     */
    SystemParam getCurrentSysParam();

    /**
     * 根据id获取当前启用的系统参数
     * @param id id
     * @return
     */
    SystemParam findSysParamById(String id);

}
