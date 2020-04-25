package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.mapper.AccountDetailMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.vo.AccountDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    private AccountDetailMapper accountDetailMapper;
    @Override
    public PageInfo<AccountDetailVo> findByCondition(AccountDetailVo accountDetailVo) {
        return null;
    }

    @Override
    public void save(AccountDetailVo accountDetailVo) {
        AccountDetail accountDetail = new AccountDetail();
        BeanUtil.copyProperties(accountDetail,accountDetailVo);
        accountDetailMapper.insert(accountDetail);
    }

    @Override
    public List<AccountDetailVo> exportByCondition(AccountDetailVo accountDetailVo) {
        return null;
    }
}
