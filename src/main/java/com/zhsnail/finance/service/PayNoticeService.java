package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.vo.PayNoticeVo;

import java.util.List;
import java.util.Map;

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
     * @param id id
     * @param status 状态
     */
    void updateStatusById(String id,String status);

    /**
     * 最后一步审批
     * @param id id
     */
    void lastApprove(String id);

    /**
     * 根据id查找缴费通知单
     * @param id id
     * @return
     */
    Map findById(String id);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    List<Map> findTaskList();
}
