package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountTemp;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.mapper.AccountTempMapper;
import com.zhsnail.finance.mapper.VoucherMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.VoucherVo;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.macros.VBAMacroExtractor;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private AccountTempMapper accountTempMapper;

    private static Map<String,String> transMap;

    static {
        transMap = new HashMap<>();
        transMap.put(DICT.VOUCHER_BIZ_TYPE_CHARGE_PAY,"收费付款");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_DEPRECIATED,"固定资产折旧");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_PURCHASE,"固定资产采购");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_REG,"固定资产登记");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_MANUAL_VOUCHER,"手工凭证");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_SALARY_PAY,"工资付款");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_BANK,"银行");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_CASH,"现金");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_OTHER,"其他");
        transMap.put(DICT.VOUCHER_POST_STATUS_UNPOST,"未过账");
        transMap.put(DICT.VOUCHER_POST_STATUS_POSTED,"已过账");
        transMap.put(DICT.STATUS_EXE,"审核通过");
        transMap.put(DICT.STATUS_CMT,"审核中");
        transMap.put(DICT.STATUS_BACK,"退回");
        transMap.put(DICT.STATUS_DRAFT,"草稿");
    }

    @Override
    public void saveOrUpdate(VoucherVo voucherVo) {
        Voucher voucher = new Voucher();
        BeanUtil.copyProperties(voucher,voucherVo);
        voucher.setStatus(DICT.STATUS_DRAFT);
        voucher.setPostingStatus(DICT.VOUCHER_POST_STATUS_UNPOST);
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_MANUAL_VOUCHER);
        List<AccountTemp> accountTempList = voucherVo.getAccountTempList();
        if (StringUtils.isNotBlank(voucher.getId())){
            voucherMapper.updateByPrimaryKeySelective(voucher);
            accountTempMapper.deleteByVoucherId(voucher.getId());
        }else {
            voucher.setCode(CodeUtil.getVoucherCode());
            voucher.setId(CodeUtil.getId());
            voucherMapper.insert(voucher);
        }
        if (CollectionUtils.isNotEmpty(accountTempList)){
            for (AccountTemp accountTemp:accountTempList){
                accountTemp.setId(CodeUtil.getId());
                accountTemp.setVoucherId(voucher.getId());
            }
        }
        accountTempMapper.batchInsert(accountTempList);
    }


    @Override
    public void commitVoucher(VoucherVo voucherVo) {

    }

    @Override
    public void updateStatusById(String id, String status) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        voucher.setStatus(status);
        voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public PageInfo<VoucherVo> findByCondition(VoucherVo voucherVo) {
        CommonUtil.startPage(voucherVo);
        List<Voucher> voucherList = voucherMapper.findAllByCondition(voucherVo);
        PageInfo<Voucher> voucherPageInfo = new PageInfo<>(voucherList);
        long total = voucherPageInfo.getTotal();
        List<VoucherVo> voucherVoList = new ArrayList<>();
        voucherList.forEach(voucher -> {
            VoucherVo temp = new VoucherVo();
            BeanUtil.copyProperties(temp,voucher);
            transString(temp);
            voucherVoList.add(temp);
        });
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(voucherVoList);
        voucherVoPageInfo.setTotal(total);
        return voucherVoPageInfo;
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

    /**
     * 翻译
     * @param voucherVo
     */
    private void transString(VoucherVo voucherVo){
        voucherVo.setOriginatorName((String) CommonUtil.findUserInfo(voucherVo.getOriginator()).get("name"));
        voucherVo.setAuditerName((String) CommonUtil.findUserInfo(voucherVo.getAuditer()).get("name"));
        voucherVo.setKeeperName((String) CommonUtil.findUserInfo(voucherVo.getKeeper()).get("name"));
        voucherVo.setBizName(transMap.get(voucherVo.getBizType()));
        voucherVo.setDealName(transMap.get(voucherVo.getDealType()));
        voucherVo.setStatus(transMap.get(voucherVo.getStatus()));
        voucherVo.setPostingStatus(transMap.get(voucherVo.getPostingStatus()));
        if (CollectionUtils.isNotEmpty(voucherVo.getAccountTempList())){
            for (AccountTemp accountTemp:voucherVo.getAccountTempList()){
                Account account = accountTemp.getAccount();
                String accountLongName = CommonUtil.getAccountLongName(account);
                account.setAccountName(accountLongName);
            }
        }
    }

    @Override
    public VoucherVo findById(String id) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        VoucherVo voucherVo = new VoucherVo();
        BeanUtil.copyProperties(voucherVo,voucher);
        transString(voucherVo);
        return voucherVo;
    }

    @Override
    public PageInfo<VoucherVo> findUnpostVoucher(VoucherVo voucherVo) {
        CommonUtil.startPage(voucherVo);
        List<Voucher> unPostVoucherList = voucherMapper.findUnpostVoucherList(voucherVo);
        PageInfo<Voucher> voucherPageInfo = new PageInfo<>(unPostVoucherList);
        long total = voucherPageInfo.getTotal();
        List<VoucherVo> voucherVoList = new ArrayList<>();
        unPostVoucherList.forEach(voucher -> {
            VoucherVo temp = new VoucherVo();
            BeanUtil.copyProperties(temp,voucher);
            transString(temp);
            voucherVoList.add(temp);
        });
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(voucherVoList);
        voucherPageInfo.setTotal(total);
        return voucherVoPageInfo;
    }
}
