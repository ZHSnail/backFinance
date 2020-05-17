package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.vo.VoucherVo;

import java.util.List;
import java.util.Map;

public interface VoucherService {
    /**
     * 暂存或更新凭证
     * @param voucherVo
     */
    void saveOrUpdate(VoucherVo voucherVo);

    /**
     * 提交凭证到工作流
     * @param voucherVo
     */
    void commitVoucher(VoucherVo voucherVo);

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
     * 根据条件分页查询凭证
     * @param voucherVo 凭证
     * @return
     */
    PageInfo<VoucherVo> findByCondition(VoucherVo voucherVo);

    /**
     * 查询任务列表 草稿、审核中、已完成
     * @return
     */
    Map findTaskMapList();

    /**
     * 生成单个凭证
     * @param voucher 凭证
     * @param debitAccountId 借方会计科目id
     * @param creditAccountId 贷方会计科目id
     */
    void generateVoucher(Voucher voucher,String debitAccountId,String creditAccountId);

    /**
     * 根据id查找凭证
     * @param id
     * @return
     */
    VoucherVo findById(String id);

    /**
     * 分页查询待过账的凭证 凭证界面
     * @param voucherVo
     * @return
     */
    PageInfo<VoucherVo> findUnpostVoucher(VoucherVo voucherVo);

    /**
     * 分页条件查询当前用户的所有任务列表
     * @param voucherVo
     * @return
     */
    PageInfo<VoucherVo> findTaskListByCondition(VoucherVo voucherVo);

    /**
     * 分页查询待审批的流程
     * @param voucherVo
     * @return
     */
    PageInfo<VoucherVo> findCmtTaskList(VoucherVo voucherVo);

    /**
     * 分页查询出纳页面所需要的凭证
     * @param voucherVo
     * @return
     */
    PageInfo<VoucherVo> findCashierList(VoucherVo voucherVo,String tickState);

    /**
     * 根据id勾对凭证
     * @param id
     */
    void tickVoucher(String id);
}
