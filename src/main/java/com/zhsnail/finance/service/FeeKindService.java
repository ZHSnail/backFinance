package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.FeeKind;
import com.zhsnail.finance.vo.FeeKindVo;

import java.util.List;

public interface FeeKindService {
    /**
     * 删除宿舍信息
     * @param id 宿舍信息id
     */
    void deleteFeeKind(String id);

    /**
     * 修改宿舍信息
     * @param feeKindVo 宿舍信息
     */
    void updateFeeKind(FeeKindVo feeKindVo);

    /**
     * 保存宿舍信息
     * @param feeKindVo 宿舍信息
     */
    void saveFeeKind(FeeKindVo feeKindVo);

    /**
     * 分页查询所有宿舍信息
     * @param feeKindVo 宿舍信息
     * @return
     */
    PageInfo<FeeKind> findByCondition(FeeKindVo feeKindVo);

    /**
     * 查询所有信息
     * @return
     */
    List<FeeKind> findAll();
}
