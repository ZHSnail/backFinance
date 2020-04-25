package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.vo.VoucherVo;

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
}
