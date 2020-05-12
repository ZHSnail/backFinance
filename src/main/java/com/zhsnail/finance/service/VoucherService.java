package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
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
    PageInfo<Voucher> findByCondition(VoucherVo voucherVo);

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

}
