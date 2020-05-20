package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.PayStubInfoVo;

import java.util.List;

public interface PayStubInfoService {
    /**
     * 删除工资单信息
     * @param id 工资单信息id
     */
    void deletePayStubInfo(String id);

    /**
     * 修改工资单信息
     * @param payStubInfoVo 工资单信息
     */
    void updatePayStubInfo(PayStubInfoVo payStubInfoVo);

    /**
     * 保存工资单信息
     * @param payStubInfoVo 工资单信息
     */
    void savePayStubInfo(PayStubInfoVo payStubInfoVo);

    /**
     * 分页查询所有工资单信息
     * @param payStubInfoVo 工资单信息
     * @return
     */
    PageInfo<PayStubInfoVo> findAllByCondition(PayStubInfoVo payStubInfoVo);

    /**
     * 查询所有工资单
     * @return
     */
    List<PayStubInfoVo> findAll();
}
