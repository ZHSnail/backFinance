package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.StaffInfo;
import com.zhsnail.finance.vo.StaffInfoVo;

import java.util.List;
import java.util.Map;

public interface StaffInfoService {
    /**
     * 删除员工信息
     * @param id 员工信息id
     */
    void deleteStaffInfo(String id);

    /**
     * 修改员工信息
     * @param staffInfoVo 员工信息
     */
    void updateStaffInfo(StaffInfoVo staffInfoVo);

    /**
     * 保存员工信息
     * @param staffInfoVo 员工信息
     */
    void saveStaffInfo(StaffInfoVo staffInfoVo);

    /**
     * 分页查询所有员工信息
     * @param staffInfoVo 员工信息
     * @return
     */
    PageInfo<StaffInfoVo> findAllByCondition(StaffInfoVo staffInfoVo);

    /**
     * 查询所有员工信息
     * @return
     */
    List<StaffInfo> findAll();

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    StaffInfoVo findById(String id);
}
