package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.AssetsDepreciationVo;

import java.util.Map;

public interface AssetsDepreciationService {
    /**
     * 保存或更新
     * @param assetsDepreciationVo
     */
    void saveOrUpdate(AssetsDepreciationVo assetsDepreciationVo);

    /**
     * 提交到工作流
     * @param assetsDepreciationVo
     */
    void commitAssetsDepreciation(AssetsDepreciationVo assetsDepreciationVo);

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
    AssetsDepreciationVo findById(String id);

    /**
     * 分页条件查询
     * @param assetsDepreciationVo
     * @return
     */
    PageInfo<AssetsDepreciationVo> findByCondition(AssetsDepreciationVo assetsDepreciationVo);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    Map findTaskMapList();

    /**
     * 分页查询待审批的流程
     * @param assetsDepreciationVo
     * @return
     */
    PageInfo<AssetsDepreciationVo> findCmtTaskList(AssetsDepreciationVo assetsDepreciationVo);

    /**
     * 分页条件查询当前用户的所有任务列表
     * @param assetsDepreciationVo
     * @return
     */
    PageInfo<AssetsDepreciationVo> findTaskListByCondition(AssetsDepreciationVo assetsDepreciationVo);

}
