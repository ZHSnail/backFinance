package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.PayDetail;
import com.zhsnail.finance.vo.PayDetailVo;

import java.util.List;

public interface PayDetailService {
    /**
     * 根据缴费通知id分页查询
     * @param payDetailVo
     * @return
     */
    PageInfo<PayDetailVo> findByPayNoticeId(PayDetailVo payDetailVo);

    /**
     * 根据id查询
     * @param id id
     * @return
     */
    PayDetailVo findById(String id);

    /**
     * 付款
     * @param id id
     */
    void execPay(String id);

    /**
     * 根据当前用户id查询缴费单
     * @return
     */
    PageInfo<PayDetail> findByUserId(PageEntity pageEntity);
}
