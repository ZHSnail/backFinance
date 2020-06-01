package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.mapper.AccountDetailMapper;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AccountDetailVo;
import com.zhsnail.finance.vo.AccountDetailVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    private AccountDetailMapper accountDetailMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public PageInfo<AccountDetailVo> findByCondition(AccountDetailVo accountDetailVo) {
        Account account = accountMapper.findByCode(accountDetailVo.getAccountCode());
        if (account !=null){
            accountDetailVo.setAccountId(account.getId());
        }
        CommonUtil.startPage(accountDetailVo);
        List<AccountDetail> accountDetails = accountDetailMapper.findByCondition(accountDetailVo);
        PageInfo<AccountDetail> accountDetailPageInfo = new PageInfo<>(accountDetails);
        long total = accountDetailPageInfo.getTotal();
        List<AccountDetailVo> list = arrangeData(accountDetails);
        PageInfo<AccountDetailVo> accountDetailVoPageInfo = new PageInfo<>(list);
        accountDetailVoPageInfo.setTotal(total);
        return accountDetailVoPageInfo;
    }

    private List<AccountDetailVo> arrangeData(List<AccountDetail> accountDetails){
        List<AccountDetailVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accountDetails)){
            accountDetails.forEach(accountDetail -> {
                AccountDetailVo accountDetailVo = new AccountDetailVo();
                BeanUtil.copyProperties(accountDetailVo,accountDetail);
                Account account = accountDetailVo.getAccountBalance().getAccount();
                accountDetailVo.setAccountCode(account.getCode());
                accountDetailVo.setAccountName(CommonUtil.getAccountLongName(account));
                accountDetailVo.setVoucherCode(accountDetailVo.getVoucher().getCode());
                accountDetailVo.setAccountPeriod(accountDetailVo.getVoucher().getAccountPeriod());
                accountDetailVo.setOriginator((String) CommonUtil.findUserInfo(accountDetailVo.getVoucher().getOriginator()).get("name"));
                accountDetailVo.setPostingDate(accountDetailVo.getVoucher().getPostingDate());
                accountDetailVo.setMemo(accountDetailVo.getVoucher().getMemo());
                list.add(accountDetailVo);
            });
        }
        return list;
    }

    @Override
    public void save(AccountDetailVo accountDetailVo) {
        AccountDetail accountDetail = new AccountDetail();
        BeanUtil.copyProperties(accountDetail,accountDetailVo);
        accountDetailMapper.insert(accountDetail);
    }

    @Override
    public List<AccountDetailVo> exportByCondition(AccountDetailVo accountDetailVo) {
        Account account = accountMapper.findByCode(accountDetailVo.getAccountCode());
        if (account !=null){
            accountDetailVo.setAccountId(account.getId());
        }
        List<AccountDetail> accountDetails = accountDetailMapper.findByCondition(accountDetailVo);
        List<AccountDetailVo> list = arrangeData(accountDetails);
        return list;
    }
}
