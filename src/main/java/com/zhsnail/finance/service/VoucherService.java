package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.VoucherVo;

import java.util.List;
import java.util.Map;

public interface VoucherService {
    /**
     * 暂存凭证
     * @param voucherVo
     */
    void saveVoucher(VoucherVo voucherVo);

    /**
     * 提交凭证到工作流
     * @param voucherVo
     */
    void commitVoucher(VoucherVo voucherVo);

    /**
     * 修改状态
     * @param status 状态
     */
    void updateStatusById(String id,String status);

    /**
     * 根据条件分页查询凭证
     * @param voucherVo 凭证
     * @return
     */
    PageInfo<VoucherVo> findByCondition(VoucherVo voucherVo);

    /**
     * 查询凭证审核的任务列表 xx查询里的
     * @param voucherVo
     * @return
     */
    List<VoucherVo> queryTaskList(VoucherVo voucherVo);

    /**
     * 分类查询任务 草稿/审核中
     * @param voucherVo
     * @return
     */
    List<Map> findTaskBy(VoucherVo voucherVo);

    /**
     * 根据id审核通过
     * @param id
     */
    void approveReq(String id);

    /**
     * 根据id拒绝审批
     * @param id
     */
    void refuseReq(String id);

    /**
     * 根据id测回审批
     * @param id
     */
    void revokeReq(String id);
}
