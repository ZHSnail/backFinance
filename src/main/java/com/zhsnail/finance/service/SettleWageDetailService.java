package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.SettleWageDetail;
import com.zhsnail.finance.vo.SettleWageDetailVo;

import java.util.List;
import java.util.Map;

public interface SettleWageDetailService {

    /**
     * 分页查询所有员工信息
     * @param settleWageDetailVo 员工信息
     * @return
     */
    PageInfo<Map> findAllByCondition(SettleWageDetailVo settleWageDetailVo);

}
