package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.OrgInfoVo;

import java.util.List;

public interface OrgInfoService {
    /**
     * 删除部门信息
     * @param id 部门信息id
     */
    void deleteOrgInfo(String id);

    /**
     * 修改部门信息
     * @param orgInfoVo 部门信息
     */
    void updateOrgInfo(OrgInfoVo orgInfoVo);

    /**
     * 保存部门信息
     * @param orgInfoVo 部门信息
     */
    void saveOrgInfo(OrgInfoVo orgInfoVo);

    /**
     * 分页查询所有部门信息
     * @param orgInfoVo 部门信息
     * @return
     */
    PageInfo<OrgInfoVo> findAllByCondition(OrgInfoVo orgInfoVo);

    /**
     * 查询所有部门
     * @return
     */
    List<OrgInfoVo> findAll();
}
