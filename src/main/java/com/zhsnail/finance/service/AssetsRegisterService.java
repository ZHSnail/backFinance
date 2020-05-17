package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import com.zhsnail.finance.vo.AssetsVo;

import java.util.Map;

public interface AssetsRegisterService {
    /**
     * 保存或更新
     * @param assetsRegisterVo
     */
    void saveOrUpdate(AssetsRegisterVo assetsRegisterVo);

    /**
     * 提交到工作流
     * @param assetsRegisterVo
     */
    void commitAssetsRegister(AssetsRegisterVo assetsRegisterVo);

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
     * 根据id查询
     * @param id
     * @return
     */
    AssetsRegisterVo findById(String id);

    /**
     * 分页条件查询
     * @param assetsRegisterVo
     * @return
     */
    PageInfo<AssetsRegisterVo> findByCondition(AssetsRegisterVo assetsRegisterVo);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    Map findTaskMapList();

    /**
     * 分页查询待审批的流程
     * @param assetsRegisterVo
     * @return
     */
    PageInfo<AssetsRegisterVo> findCmtTaskList(AssetsRegisterVo assetsRegisterVo);

    /**
     * 分页条件查询当前用户的所有任务列表
     * @param assetsRegisterVo
     * @return
     */
    PageInfo<AssetsRegisterVo> findTaskListByCondition(AssetsRegisterVo assetsRegisterVo);

}
