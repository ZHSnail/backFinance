package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.vo.AccountVo;

import java.util.List;
import java.util.Map;

public interface LenderService {
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
    void deleteAccount(String id);

    /**
     * 根据条件查找所有的会计科目并生成树形表格所需的会计科目数据
     * @param accountVo 会计科目条件
     * @return 树形表格所需会计科目数据
     */
    List<Map> findArrangeAccount(AccountVo accountVo);
}
