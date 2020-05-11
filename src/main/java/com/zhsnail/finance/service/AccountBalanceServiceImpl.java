package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.UpdateCache;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.mapper.AccountBalanceMapper;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AccountBalanceVo;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        CommonUtil.startPage(accountBalanceVo);
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
                Account account = accountBalance.getAccount();
                balanceVo.setAccountCode(accountBalance.getAccount().getCode());
                balanceVo.setAccountName(CommonUtil.getAccountLongName(account));
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
        List<Account> allDetailAccount = accountMapper.findAllDetailAccount(new AccountVo());
        List<String> accountIds = allDetailAccount.stream().map(account -> account.getId()).collect(Collectors.toList());
        accountBalanceVo.setAccountIds(accountIds);
        CommonUtil.startPage(accountBalanceVo);
        List<AccountBalance> accountBalances = accountBalanceMapper.findByCondition(accountBalanceVo);
        PageInfo<AccountBalance> accountBalancePageInfo = new PageInfo<>(accountBalances);
        long total = accountBalancePageInfo.getTotal();
        List<AccountBalanceVo> list = arrangeData(accountBalances);
        PageInfo<AccountBalanceVo> accountBalanceVoPageInfo = new PageInfo<>(list);
        accountBalanceVoPageInfo.setTotal(total);
        return accountBalanceVoPageInfo;
    }

    @Override
    public void execBatchUpdate(List<AccountBalanceVo> accountBalanceVoList) {
        List<AccountBalanceVo> notDetailAccBalanceVoList = new ArrayList<>();
        //更新父级科目对应的金额
        for (int i = 0;i<accountBalanceVoList.size();i++){
            AccountBalanceVo accountBalanceVo = accountBalanceVoList.get(i);
            String accountId = accountBalanceVo.getAccountId();
            List<String> parentIds = new ArrayList<>();
            CommonUtil.getParentIds(accountId,parentIds);
            for (int j = 0;j<parentIds.size();j++){
                int flag = j;
                List<AccountBalanceVo> collect = notDetailAccBalanceVoList.stream().filter(notDetailAccBalanceVo -> parentIds.get(flag).equals(notDetailAccBalanceVo.getAccountId())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect)){
                    countParentAmt(collect.get(0),accountBalanceVo);
                }else {
                    AccountBalanceVo temp = new AccountBalanceVo();
                    temp.setAccountId(parentIds.get(j));
                    countParentAmt(temp,accountBalanceVo);
                    temp.setAccountPeriod(accountBalanceVo.getAccountPeriod());
                    notDetailAccBalanceVoList.add(temp);
                }
            }
        }
        accountBalanceVoList.addAll(notDetailAccBalanceVoList);
        accountBalanceMapper.batchUpdate(accountBalanceVoList);
    }

    /**
     * 父级会计科目累计期初余额、年初余额、本年累计发生额
     * @param parentAccBalVo 父级科目
     * @param currentAccBalVo 当前科目
     * @return
     */
    private void countParentAmt(AccountBalanceVo parentAccBalVo,AccountBalanceVo currentAccBalVo){
        //借方期初余额
        if (currentAccBalVo.getDebitStaperiodAmt()!=null){
            BigDecimal debitStaperiodAmt = parentAccBalVo.getDebitStaperiodAmt();
            if (debitStaperiodAmt == null){
                debitStaperiodAmt = new BigDecimal("0");
            }
            parentAccBalVo.setDebitStaperiodAmt(debitStaperiodAmt.add(currentAccBalVo.getDebitStaperiodAmt()));
        }
        //贷方期初余额
        if (currentAccBalVo.getCreditStaperiodAmt()!=null){
            BigDecimal creditStaperiodAmt = parentAccBalVo.getCreditStaperiodAmt();
            if (creditStaperiodAmt == null){
                creditStaperiodAmt = new BigDecimal("0");
            }
            parentAccBalVo.setCreditStaperiodAmt(creditStaperiodAmt.add(currentAccBalVo.getCreditStaperiodAmt()));
        }
        //借方年初余额
        if (currentAccBalVo.getDebitStayearAmt()!=null){
            BigDecimal debitStayearAmt = parentAccBalVo.getDebitStayearAmt();
            if (debitStayearAmt == null){
                debitStayearAmt = new BigDecimal("0");
            }
            parentAccBalVo.setDebitStayearAmt(debitStayearAmt.add(currentAccBalVo.getDebitStayearAmt()));
        }
        //贷方年初余额
        if (currentAccBalVo.getCreditStayearAmt()!=null){
            BigDecimal creditStayearAmt = parentAccBalVo.getCreditStayearAmt();
            if (creditStayearAmt == null){
                creditStayearAmt = new BigDecimal("0");
            }
            parentAccBalVo.setCreditStayearAmt(creditStayearAmt.add(currentAccBalVo.getCreditStayearAmt()));
        }
        //借方本年累计发生额
        if (currentAccBalVo.getDebitAccumyearAmt()!=null){
            BigDecimal debitAccumyearAmt = parentAccBalVo.getDebitAccumyearAmt();
            if (debitAccumyearAmt == null){
                debitAccumyearAmt = new BigDecimal("0");
            }
            parentAccBalVo.setDebitAccumyearAmt(debitAccumyearAmt.add(currentAccBalVo.getDebitAccumyearAmt()));
        }
        //贷方本年累计发生额
        if (currentAccBalVo.getCreditAccumyearAmt()!=null){
            BigDecimal creditAccumyearAmt = parentAccBalVo.getCreditAccumyearAmt();
            if (creditAccumyearAmt == null){
                creditAccumyearAmt = new BigDecimal("0");
            }
            parentAccBalVo.setCreditAccumyearAmt(creditAccumyearAmt.add(currentAccBalVo.getCreditAccumyearAmt()));
        }
    }
}
