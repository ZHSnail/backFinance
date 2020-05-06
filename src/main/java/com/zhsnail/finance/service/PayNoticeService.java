package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.vo.PayNoticeVo;

public interface PayNoticeService {
    /**
     * 保存缴费单通知
     * @param payNoticeVo
     */
    void saveOrUpdate(PayNoticeVo payNoticeVo);

    /**
     *提交缴费单通知
     * @param payNoticeVo
     */
    void commit(PayNoticeVo payNoticeVo);

    /**
     * 删除缴费单通知
     * @param id
     */
    void deletePayNotice(String id);

    /**
     * 更新状态
     * @param id
     */
    void updateStatusById(String id);

    /**
     * 最后一步审批
     * @param id
     */
    void lastApprove(String id);

    /**
     * 根据id查找缴费通知单
     * @param id
     * @return
     */
    PayNotice findById(String id);
}
