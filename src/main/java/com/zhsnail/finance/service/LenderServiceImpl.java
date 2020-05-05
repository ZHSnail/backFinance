package com.zhsnail.finance.service;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        account.setId(CodeUtil.getId());
        accountMapper.insert(account);
    }

    @Override
    public Result deleteAccount(String id) {
        List<Account> accounts = accountMapper.findByParentId(id);
        if (CollectionUtils.isNotEmpty(accounts)){
            return new Result(false,"该科目有子科目，请先删除子科目！");
        }else {
            accountMapper.deleteByPrimaryKey(id);
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
            arrangeAccount(root,accounts);
            return root;
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
        if (rootMap != null) {
            for (Map map : rootMap) {
                String id = (String) map.get("id");
                List<Map> childrenMap = accounts.stream().filter(account -> account.getParentId().equals(id)).map(account -> {
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
    public void execBatchInsert(List<Account> accounts) {
        if (CollectionUtils.isNotEmpty(accounts)){
            accountMapper.batchInsert(accounts);
        }
    }

    @Override
    public List<Account> findDetailAccount() {
        return accountMapper.findAllDetailAccount();
    }
}
