package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.AssetsPurchaseVo;

import java.util.Map;

public interface AssetsPurchaseService {
    /**
     * 保存或更新
     * @param assetsPurchaseVo
     */
    void saveOrUpdate(AssetsPurchaseVo assetsPurchaseVo);

    /**
     * 提交到工作流
     * @param assetsPurchaseVo
     */
    void commitAssetsPurchase(AssetsPurchaseVo assetsPurchaseVo);

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
    AssetsPurchaseVo findById(String id);

    /**
     * 分页条件查询
     * @param assetsPurchaseVo
     * @return
     */
    PageInfo<AssetsPurchaseVo> findByCondition(AssetsPurchaseVo assetsPurchaseVo);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    Map findTaskMapList();

    /**
     * 分页查询待审批的流程
     * @param assetsPurchaseVo
     * @return
     */
    PageInfo<AssetsPurchaseVo> findCmtTaskList(AssetsPurchaseVo assetsPurchaseVo);

    /**
     * 分页条件查询当前用户的所有任务列表
     * @param assetsPurchaseVo
     * @return
     */
    PageInfo<AssetsPurchaseVo> findTaskListByCondition(AssetsPurchaseVo assetsPurchaseVo);

}
