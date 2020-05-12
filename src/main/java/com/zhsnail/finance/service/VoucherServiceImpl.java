package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.AccountTemp;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.mapper.AccountTempMapper;
import com.zhsnail.finance.mapper.VoucherMapper;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.poi.poifs.macros.VBAMacroExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private AccountTempMapper accountTempMapper;

    @Override
    public void saveOrUpdate(VoucherVo voucherVo) {

    }

    @Override
    public void commitVoucher(VoucherVo voucherVo) {

    }

    @Override
    public void updateStatusById(String id, String status) {

    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public PageInfo<Voucher> findByCondition(VoucherVo voucherVo) {
        CommonUtil.startPage(voucherVo);
        return null;
    }

    @Override
    public Map findTaskMapList() {
        return null;
    }

    @Override
    public void generateVoucher(Voucher voucher, String debitAccountId, String creditAccountId) {
        voucherMapper.insert(voucher);
        Map<String,String> accountIdMap = new HashMap<>();
        accountIdMap.put(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT,debitAccountId);
        accountIdMap.put(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT,creditAccountId);
        Map<String, BigDecimal> amountMap = new HashMap<>();
        amountMap.put(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT,voucher.getDebitTotal());
        amountMap.put(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT,voucher.getCreditTotal());
        List<AccountTemp> accountTemps = CommonUtil.initAccountTemp(voucher.getId(), accountIdMap, amountMap);
        accountTempMapper.batchInsert(accountTemps);
    }
}
