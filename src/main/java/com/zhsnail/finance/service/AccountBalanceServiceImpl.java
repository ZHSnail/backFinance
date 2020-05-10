package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.mapper.AccountBalanceMapper;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.vo.AccountBalanceVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {
    @Autowired
    private AccountBalanceMapper accountBalanceMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void updateByAccId(AccountBalanceVo accountBalanceVo) {
        accountBalanceMapper.updateByAccId(accountBalanceVo);
    }

    @Override
    public PageInfo<AccountBalanceVo> findByCondition(AccountBalanceVo accountBalanceVo) {
        Account account = accountMapper.findByCode(accountBalanceVo.getAccountCode());
        if (account !=null){
            accountBalanceVo.setAccountId(account.getId());
        }
        PageHelper.startPage(accountBalanceVo.getPageNum(),accountBalanceVo.getPageSize(),true);
        List<AccountBalance> accountBalances = accountBalanceMapper.findByCondition(accountBalanceVo);
        PageInfo<AccountBalance> accountBalancePageInfo = new PageInfo<>(accountBalances);
        long total = accountBalancePageInfo.getTotal();
        List<AccountBalanceVo> list = arrangeData(accountBalances);
        PageInfo<AccountBalanceVo> accountBalanceVoPageInfo = new PageInfo<>(list);
        accountBalanceVoPageInfo.setTotal(total);
        return accountBalanceVoPageInfo;
    }

    private List<AccountBalanceVo> arrangeData(List<AccountBalance> accountBalances){
        List<AccountBalanceVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accountBalances) && accountBalances.get(0).getAccount()!=null){
            accountBalances.forEach(accountBalance -> {
                AccountBalanceVo balanceVo = new AccountBalanceVo();
                BeanUtil.copyProperties(balanceVo,accountBalance);
                balanceVo.setAccountCode(accountBalance.getAccount().getCode());
                balanceVo.setAccountName(accountBalance.getAccount().getAccountName());
                balanceVo.setAccountId(accountBalance.getAccount().getId());
                balanceVo.setAccountType(accountBalance.getAccount().getType());
                list.add(balanceVo);
            });
        }
        return list;
    }
    @Override
    public void save(AccountBalanceVo accountBalanceVo) {
        AccountBalance accountBalance = new AccountBalance();
        BeanUtil.copyProperties(accountBalance,accountBalanceVo);
        accountBalanceMapper.insert(accountBalance);
    }

    @Override
    public List<AccountBalanceVo> exportByCondition(AccountBalanceVo accountBalanceVo) {
        Account account = accountMapper.findByCode(accountBalanceVo.getAccountCode());
        if (account !=null){
            accountBalanceVo.setAccountId(account.getId());
        }
        List<AccountBalance> accountBalances = accountBalanceMapper.findByCondition(accountBalanceVo);
        List<AccountBalanceVo> list = arrangeData(accountBalances);
        return list;
    }

    @Override
    public void deleteByAccId(String accountId) {
        accountBalanceMapper.deleteByaAcountId(accountId);
    }

    @Override
    public void execBatchInsert(List<AccountBalance> accountBalances) {
        if (CollectionUtils.isNotEmpty(accountBalances)){
            accountBalanceMapper.batchInsert(accountBalances);
        }
    }

    @Override
    public PageInfo<AccountBalanceVo> findInitData(AccountBalanceVo accountBalanceVo) {
        List<Account> allDetailAccount = accountMapper.findAllDetailAccount();
        List<String> accountIds = allDetailAccount.stream().map(account -> account.getId()).collect(Collectors.toList());
        accountBalanceVo.setAccountIds(accountIds);
        PageHelper.startPage(accountBalanceVo.getPageNum(),accountBalanceVo.getPageSize(),true);
        List<AccountBalance> accountBalances = accountBalanceMapper.findByCondition(accountBalanceVo);
        PageInfo<AccountBalance> accountBalancePageInfo = new PageInfo<>(accountBalances);
        long total = accountBalancePageInfo.getTotal();
        List<AccountBalanceVo> list = arrangeData(accountBalances);
        PageInfo<AccountBalanceVo> accountBalanceVoPageInfo = new PageInfo<>(list);
        accountBalanceVoPageInfo.setTotal(total);
        return accountBalanceVoPageInfo;
    }
}
