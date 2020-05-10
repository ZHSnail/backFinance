package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.vo.AccountBalanceVo;

import java.util.List;

public interface AccountBalanceService {
    /**
     * 根据会计科目id更新
     * @param accountBalanceVo
     */
    void updateByAccId(AccountBalanceVo accountBalanceVo);

    /**
     * 根据条件分页查询所有会计科目余额
     * @param accountBalanceVo
     * @return
     */
    PageInfo<AccountBalanceVo> findByCondition(AccountBalanceVo accountBalanceVo);

    /**
     * 保存会计科目余额
     * @param accountBalanceVo
     */
    void save(AccountBalanceVo accountBalanceVo);

    /**
     * 根据条件导出所有数据
     * @param accountBalanceVo
     * @return
     */
    List<AccountBalanceVo> exportByCondition(AccountBalanceVo accountBalanceVo);

    /**
     * 根据会计科目id删除会计科目余额
     * @param accountId
     */
    void deleteByAccId(String accountId);

    /**
     * 批量插入会计科目余额
     * @param accountBalances 会计科目余额信息
     */
    void execBatchInsert(List<AccountBalance>accountBalances);

    /**
     * 查询需要初始化的会计科目余额表数据
     * @param accountBalanceVo 条件
     * @return
     */
    PageInfo<AccountBalanceVo> findInitData(AccountBalanceVo accountBalanceVo);
}
