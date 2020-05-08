package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.PageEntity;
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
    Map findTaskMapList();

    /**
     * 分页查询待审批的流程
     * @param pageEntity
     * @return
     */
    PageInfo<PayNotice> findCmtTaskList(PageEntity pageEntity);

    /**
     * 条件分页查询
     * @param payNoticeVo
     * @return
     */
    PageInfo<PayNotice> findListByCondition(PayNoticeVo payNoticeVo);

    /**
     * 导出缴费通知
     * @param payNoticeVo
     * @return
     */
    List<PayNoticeVo> exportPayNoticeVo(PayNoticeVo payNoticeVo);

}
