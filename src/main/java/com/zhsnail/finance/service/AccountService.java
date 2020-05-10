package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.vo.AccountVo;

import java.util.List;
import java.util.Map;

public interface AccountService {
    /**
     * 查询所有的会计科目
     * @return  List<Account>
     */
    List<Account> findAllAccount();

    /**
     * 保存会计科目
     * @param accountVo 会计科目
     */
    void saveAccount(AccountVo accountVo);

    /**
     * 删除会计科目
     * @param id 会计科目id
     */
    Result deleteAccount(String id);

    /**
     * 根据条件查找所有的会计科目并生成树形表格所需的会计科目数据
     * @param accountVo 会计科目条件
     * @return 树形表格所需会计科目数据
     */
    List<Map> findArrangeAccount(AccountVo accountVo);

    /**
     * 查找上一级的所有会计科目
     * @param level 级次
     * @return 会计科目
     */
    List<Account> findUpAccount(String level);

    /**
     * 查询会计科目导出信息
     * @param accountVo
     * @return
     */
    List<AccountVo> exportAccount(AccountVo accountVo);

    /**
     * 批量插入会计科目信息
     * @param accounts
     */
    void execBatchInsert(List<Account> accounts);

    /**
     * 查询所有可选的会计科目
     * @return
     */
    List<Account> findDetailAccount();

    /**
     * 分页条件查询所有的会计科目信息
     * @param accountVo 会计科目参数
     * @return
     */
    PageInfo<Account> findAllByCondition(AccountVo accountVo);
}
