package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.FeeKind;
import com.zhsnail.finance.vo.FeeKindVo;

import java.util.List;

public interface FeeKindService {
    /**
     * 删除费用类别
     * @param id 费用类别id
     */
    void deleteFeeKind(String id);

    /**
     * 修改费用类别
     * @param feeKindVo 费用类别
     */
    void updateFeeKind(FeeKindVo feeKindVo);

    /**
     * 保存费用类别
     * @param feeKindVo 费用类别
     */
    void saveFeeKind(FeeKindVo feeKindVo);

    /**
     * 分页查询所有费用类别
     * @param feeKindVo 费用类别
     * @return
     */
    PageInfo<FeeKind> findByCondition(FeeKindVo feeKindVo);

    /**
     * 查询所有信息
     * @return
     */
    List<FeeKind> findAll();
}
