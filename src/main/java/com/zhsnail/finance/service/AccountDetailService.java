package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.vo.AccountDetailVo;

import java.util.List;

public interface AccountDetailService {
    /**
     * 分页条件查询
     * @param accountDetailVo
     * @return
     */
    PageInfo<AccountDetailVo> findByCondition(AccountDetailVo accountDetailVo);

    /**
     * 保存会计科目明细
     * @param accountDetailVo
     */
    void save(AccountDetailVo accountDetailVo);

    /**
     * 根据条件查询会计科目明细导出数据
     * @param accountDetailVo
     * @return
     */
    List<AccountDetailVo> exportByCondition(AccountDetailVo accountDetailVo);
}
