package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.common.UpdateCache;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountBalanceService accountBalanceService;

    @Override
    @Cacheable("accountList")
    public List<Account> findAllAccount() {
        return accountMapper.findAllByCondition(new AccountVo());
    }

    @Override
    @UpdateCache(name = "accountList",beanName = "accountServiceImpl",methodName = "findAllAccount")
    public void saveAccount(AccountVo accountVo) {
        Account account = new Account();
        BeanUtil.copyProperties(account,accountVo);
        account.setId(CodeUtil.getId());
        accountMapper.insert(account);
        accountBalanceService.execBatchInsert(generaAccountBalance(account.getId()));
    }

    /**
     * 批量生成会计科目余额实体
     * @param accountIds 会计科目余额实体
     * @return
     */
    private List<AccountBalance> generaAccountBalance(String... accountIds){
        List<AccountBalance> accountBalances = new ArrayList<>();
        for (int i = 0;i<accountIds.length;i++){
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setAccountId(accountIds[i]);
            accountBalance.setId(CodeUtil.getId());
            accountBalance.setDebitStayearAmt(new BigDecimal(0.00));
            accountBalance.setCreditStayearAmt(new BigDecimal(0.00));
            accountBalance.setCreditStaperiodAmt(new BigDecimal(0.00));
            accountBalance.setDebitStaperiodAmt(new BigDecimal(0.00));
            accountBalance.setCreditEndperiodAmt(new BigDecimal(0.00));
            accountBalance.setDebitEndperiodAmt(new BigDecimal(0.00));
            accountBalance.setCreditCurrperiodAmt(new BigDecimal(0.00));
            accountBalance.setDebitCurrperiodAmt(new BigDecimal(0.00));
            accountBalance.setDebitAccumyearAmt(new BigDecimal(0.00));
            accountBalance.setCreditAccumyearAmt(new BigDecimal(0.00));
            accountBalances.add(accountBalance);
        }
        return accountBalances;
    }
    @Override
    @UpdateCache(name = "accountList",beanName = "accountServiceImpl",methodName = "findAllAccount")
    public Result deleteAccount(String id) {
        List<Account> accounts = accountMapper.findByParentId(id);
        if (CollectionUtils.isNotEmpty(accounts)){
            return new Result(false,"该科目有子科目，请先删除子科目！");
        }else {
            accountMapper.deleteByPrimaryKey(id);
            accountBalanceService.deleteByAccId(id);
            return new Result(true,"删除成功！");
        }
    }

    @Override
    public List<Map> findArrangeAccount(AccountVo accountVo) {
        List<Account> accounts = accountMapper.findAllByCondition(accountVo);
        accounts.forEach(account -> {
            account.setIsBank(transString(account.getIsBank()));
            account.setIsCash(transString(account.getIsCash()));
            account.setIsDetail(transString(account.getIsDetail()));
        });
        if (accountVo!=null && StringUtils.isNotBlank(accountVo.getAccountName())&&StringUtils.isNotBlank(accountVo.getCode())){
            List<Map> root = accounts.stream().map(account -> {
                Map<String, Object> map = BeanUtil.beanToMap(account);
                return map;
            }).collect(Collectors.toList());
            return root;
        }else {
            List<Map> root = accounts.stream().filter(account -> !StringUtils.isNotBlank(account.getParentId())).map(account -> {
                Map<String, Object> map = BeanUtil.beanToMap(account);
                return map;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(root)){
                arrangeAccount(root,accounts);
                return root;
            }else {
                return accounts.stream().map(account -> BeanUtil.beanToMap(account)).collect(Collectors.toList());
            }


        }
    }

    private String transString(String s){
        if (DICT.BOOLEAN_STATE_TRUE.equals(s)){
            return "是";
        }else {
            return "否";
        }
    }
    private void arrangeAccount(List<Map> rootMap,List<Account> accounts){
        if (CollectionUtils.isNotEmpty(rootMap)) {
            for (Map map : rootMap) {
                String id = (String) map.get("id");
                List<Map> childrenMap = accounts.stream().filter(account -> id.equals(account.getParentId())).map(account -> {
                    return BeanUtil.beanToMap(account);
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(childrenMap)){
                    map.put("children", childrenMap);
                    arrangeAccount(childrenMap, accounts);
                }
            }
        }
    }

    @Override
    public List<Account> findUpAccount(String level) {
        return accountMapper.findUpperAccount(level);
    }

    @Override
    public List<AccountVo> exportAccount(AccountVo accountVo) {
        List<Account> accountList = accountMapper.findAllByCondition(accountVo);
        ArrayList<AccountVo> list = new ArrayList<>();
        accountList.forEach(account -> {
            AccountVo vo = new AccountVo();
            BeanUtil.copyProperties(vo,account);
            if (StringUtils.isNotBlank(account.getParentId())){
                Account parentAccount = accountMapper.selectByPrimaryKey(account.getParentId());
                vo.setParentName(parentAccount.getAccountName());
            }
            list.add(vo);
        });
        return list;
    }

    @Override
    @UpdateCache(name = "accountList",beanName = "accountServiceImpl",methodName = "findAllAccount")
    public void execBatchInsert(List<Account> accounts) {
        if (CollectionUtils.isNotEmpty(accounts)){
            accountMapper.batchInsert(accounts);
            List<String> accountIds = accounts.stream().map(account -> account.getId()).collect(Collectors.toList());
            accountBalanceService.execBatchInsert(generaAccountBalance(accountIds.toArray(new String[accountIds.size()])));
        }
    }

    @Override
    public PageInfo<Account> findDetailAccount(AccountVo accountVo) {
        CommonUtil.startPage(accountVo);
        List<Account> accountList = accountMapper.findAllDetailAccount(accountVo);
        accountList.forEach(account -> account.setAccountName(CommonUtil.getAccountLongName(account)));
        PageInfo<Account> accountPageInfo = new PageInfo<>(accountList);
        return accountPageInfo;
    }

    @Override
    public PageInfo<Account> findAllByCondition(AccountVo accountVo) {
        CommonUtil.startPage(accountVo);
        List<Account> accountList = accountMapper.findAllByCondition(accountVo);
        PageInfo<Account> accountPageInfo = new PageInfo<>(accountList);
        return accountPageInfo;
    }

    @Override
    public Map findById(String id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        Map<String, Object> map = BeanUtil.beanToMap(account);
        map.put("name",CommonUtil.getAccountLongName(account));
        return map;
    }
}
