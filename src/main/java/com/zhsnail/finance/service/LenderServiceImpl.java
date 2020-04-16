package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LenderServiceImpl implements LenderService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllByCondition(new AccountVo());
    }

    @Override
    public void saveAccount(AccountVo accountVo) {
        Account account = new Account();
        BeanUtil.copyProperties(account,accountVo);
        accountMapper.insert(account);
    }

    @Override
    public void deleteAccount(String id) {
        accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map> findArrangeAccount(AccountVo accountVo) {
        List<Account> accounts = accountMapper.findAllByCondition(accountVo);
        return null;
    }

    private List<Map> arrangeAccount(List<Account> accounts){
        return new ArrayList<>();
    }
}
