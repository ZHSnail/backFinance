package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.AssetsChangeVo;

import java.util.Map;

public interface AssetsChangeService {
    /**
     * 保存或更新
     * @param assetsChangeVo
     */
    void saveOrUpdate(AssetsChangeVo assetsChangeVo);

    /**
     * 提交到工作流
     * @param assetsChangeVo
     */
    void commitAssetsChange(AssetsChangeVo assetsChangeVo);

    /**
     * 更新状态
     * @param id id
     * @param status 状态
     */
    void updateStatusById(String id, String status);

    /**
     * 最后一步审批
     * @param id id
     */
    void lastApprove(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AssetsChangeVo findById(String id);

    /**
     * 分页条件查询
     * @param assetsChangeVo
     * @return
     */
    PageInfo<AssetsChangeVo> findByCondition(AssetsChangeVo assetsChangeVo);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    Map findTaskMapList();

    /**
     * 分页查询待审批的流程
     * @param assetsChangeVo
     * @return
     */
    PageInfo<AssetsChangeVo> findCmtTaskList(AssetsChangeVo assetsChangeVo);

    /**
     * 分页条件查询当前用户的所有任务列表
     * @param assetsChangeVo
     * @return
     */
    PageInfo<AssetsChangeVo> findTaskListByCondition(AssetsChangeVo assetsChangeVo);

}
