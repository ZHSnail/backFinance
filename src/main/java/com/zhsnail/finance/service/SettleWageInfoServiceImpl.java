package com.zhsnail.finance.service;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.AccountTempMapper;
import com.zhsnail.finance.mapper.SettleWageDetailMapper;
import com.zhsnail.finance.mapper.SettleWageInfoMapper;
import com.zhsnail.finance.mapper.VoucherMapper;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleWageInfoServiceImpl implements SettleWageInfoService {
    @Autowired
    private SettleWageInfoMapper settleWageInfoMapper;
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private SettleWageDetailMapper settleWageDetailMapper;
    @Autowired
    private AccountTempMapper accountTempMapper;

    @Override
    public List<SettleWageInfo> findByPayStuId(String payStubInfoId) {
        return settleWageInfoMapper.findByPayStuId(payStubInfoId);
    }

    @Override
    public void execPaySalary(String id) {
        SettleWageInfo settleWageInfo = settleWageInfoMapper.selectByPrimaryKey(id);
        List<StaffInfo> staffInfoList = settleWageInfo.getStaffInfoList();
        if (CollectionUtils.isEmpty(staffInfoList)){
            throw new BaseRuningTimeException("当前没有可结算的员工");
        }
        PayStubInfo payStubInfo = settleWageInfo.getPayStubInfo();
        List<SettleWageDetail> settleWageDetailList = new ArrayList<>();
        BigDecimal total = new BigDecimal("0");
        for (StaffInfo staffInfo : staffInfoList) {
            List<AccountTemp> accountTempList = new ArrayList<>();
            SettleWageDetail settleWageDetail = new SettleWageDetail();
            //基本工资
            BigDecimal postWageAmount = staffInfo.getPostWageAmount();
            List<FloatWage> floatWageList = payStubInfo.getFloatWageList();
            //单位缴纳项 实发不用计算只需生成凭证
            List<FloatWage> unitPayFloatList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_UNIT_PAY.equals(floatWage.getSignType())).collect(Collectors.toList());
            //扣减项
            List<FloatWage> deductFloatList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_DEDUCT.equals(floatWage.getSignType())).collect(Collectors.toList());
            //应发项
            List<FloatWage> shouldPaidFloatList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID.equals(floatWage.getSignType())).collect(Collectors.toList());
            //应发金额
            BigDecimal payableAmount = new BigDecimal("0");
            //用来计税的金额
            BigDecimal taxTempAmount = new BigDecimal("0");
            //扣减项
            BigDecimal deductionAmount = new BigDecimal("0");
            //应纳税额
            BigDecimal taxableAmount = new BigDecimal("0");
            ;
            //实发金额
            BigDecimal paidAmount = new BigDecimal("0");
            payableAmount = payableAmount.add(postWageAmount);
            taxTempAmount = taxTempAmount.add(postWageAmount);
            if (CollectionUtils.isNotEmpty(shouldPaidFloatList)) {
                for (FloatWage floatWage : shouldPaidFloatList) {
                    payableAmount = payableAmount.add(floatWage.getAmount());
                    if (DICT.FLOAT_WAGE_TAX_TYPE_TAX.equals(floatWage.getTaxType())) {
                        taxTempAmount = taxTempAmount.add(floatWage.getAmount());
                    }
                    if (DICT.FLOAT_WAGE_TAX_TYPE_PRE_TAX_DED.equals(floatWage.getTaxType())) {
                        taxTempAmount = taxTempAmount.subtract(floatWage.getAmount());
                    }
                    AccountTemp accountTemp = new AccountTemp();
                    accountTemp.setAccountId(floatWage.getCreditAccountId());
                    accountTemp.setCreditAmt(floatWage.getAmount());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT);
                    accountTempList.add(accountTemp);
                    accountTemp = new AccountTemp();
                    accountTemp.setDebitAmt(floatWage.getAmount());
                    accountTemp.setAccountId(floatWage.getDebitAccountId());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT);
                    accountTempList.add(accountTemp);
                }
            }
            if (CollectionUtils.isNotEmpty(deductFloatList)) {
                for (FloatWage floatWage : deductFloatList) {
                    deductionAmount = deductionAmount.add(floatWage.getAmount());
                    AccountTemp accountTemp = new AccountTemp();
                    accountTemp.setAccountId(floatWage.getCreditAccountId());
                    accountTemp.setCreditAmt(floatWage.getAmount());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT);
                    accountTempList.add(accountTemp);
                    accountTemp = new AccountTemp();
                    accountTemp.setDebitAmt(floatWage.getAmount());
                    accountTemp.setAccountId(floatWage.getDebitAccountId());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT);
                    accountTempList.add(accountTemp);
                }
            }
            if (CollectionUtils.isNotEmpty(unitPayFloatList)) {
                for (FloatWage floatWage : unitPayFloatList) {
                    AccountTemp accountTemp = new AccountTemp();
                    accountTemp.setAccountId(floatWage.getCreditAccountId());
                    accountTemp.setCreditAmt(floatWage.getAmount());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT);
                    accountTempList.add(accountTemp);
                    accountTemp = new AccountTemp();
                    accountTemp.setDebitAmt(floatWage.getAmount());
                    accountTemp.setAccountId(floatWage.getDebitAccountId());
                    accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT);
                    accountTempList.add(accountTemp);
                }
            }
            taxTempAmount = taxTempAmount.subtract(deductionAmount);
            taxTempAmount = taxTempAmount.subtract(new BigDecimal("3500"));
            if (taxTempAmount.compareTo(new BigDecimal("36001")) == -1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.03"));
            } else if (taxTempAmount.compareTo(new BigDecimal("144001")) == -1 && taxTempAmount.compareTo(new BigDecimal("36,000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.1"));
            } else if (taxTempAmount.compareTo(new BigDecimal("300001")) == -1 && taxTempAmount.compareTo(new BigDecimal("144000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.2"));
            } else if (taxTempAmount.compareTo(new BigDecimal("420001")) == -1 && taxTempAmount.compareTo(new BigDecimal("300000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.25"));
            } else if (taxTempAmount.compareTo(new BigDecimal("660001")) == -1 && taxTempAmount.compareTo(new BigDecimal("420000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.3"));
            } else if (taxTempAmount.compareTo(new BigDecimal("960001")) == -1 && taxTempAmount.compareTo(new BigDecimal("660000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.35"));
            } else if (taxTempAmount.compareTo(new BigDecimal("960,000")) == 1) {
                taxableAmount = taxTempAmount.multiply(new BigDecimal("0.45"));
            }
            paidAmount = payableAmount.subtract(deductionAmount).subtract(taxableAmount);
            settleWageDetail.setId(CodeUtil.getId());
            settleWageDetail.setDeductionAmount(deductionAmount);
            settleWageDetail.setPaidAmount(paidAmount);
            settleWageDetail.setPayableAmount(payableAmount);
            settleWageDetail.setTaxableAmount(taxableAmount);
            settleWageDetail.setSettleWageId(id);
            settleWageDetail.setStaffId(staffInfo.getId());
            settleWageDetailList.add(settleWageDetail);
            StationInfo stationInfo = staffInfo.getStationInfo();
            ScaleSalary scaleSalary = stationInfo.getScaleSalary();
            BigDecimal scaleAmount = scaleSalary.getScaleAmount();
            BigDecimal scaleGrad = scaleSalary.getScaleGrad();
            AccountTemp accountTemp = new AccountTemp();
            accountTemp.setAccountId(stationInfo.getCreditScaleAccId());
            accountTemp.setCreditAmt(scaleAmount.add(scaleGrad.multiply(new BigDecimal(staffInfo.getScaleLv()))));
            accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT);
            accountTempList.add(accountTemp);
            accountTemp = new AccountTemp();
            accountTemp.setDebitAmt(scaleAmount.add(scaleGrad.multiply(new BigDecimal(staffInfo.getScaleLv()))));
            accountTemp.setAccountId(stationInfo.getDebitScaleAccId());
            accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT);
            accountTempList.add(accountTemp);
            StationSalary stationSalary = stationInfo.getStationSalary();
            BigDecimal stationAmount = stationSalary.getStationAmount();
            BigDecimal stationGrad = stationSalary.getStationGrad();
            accountTemp = new AccountTemp();
            accountTemp.setAccountId(stationInfo.getCreditStationAccId());
            accountTemp.setCreditAmt(stationAmount.add(stationGrad.multiply(new BigDecimal(staffInfo.getStationLv()))));
            accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT);
            accountTempList.add(accountTemp);
            accountTemp = new AccountTemp();
            accountTemp.setDebitAmt(stationAmount.add(stationGrad.multiply(new BigDecimal(staffInfo.getStationLv()))));
            accountTemp.setAccountId(stationInfo.getDebitStationAccId());
            accountTemp.setDirection(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT);
            accountTempList.add(accountTemp);
            Voucher voucher = initVoucher(accountTempList);
            voucherMapper.insert(voucher);
            accountTempMapper.batchInsert(accountTempList);
            total = total.add(paidAmount);
        }
        settleWageInfo.setExeDate(new Date());
        settleWageInfo.setSuccessNumber(new BigDecimal(staffInfoList.size()));
        settleWageInfo.setTotalAmount(total);
        settleWageDetailMapper.batchInsert(settleWageDetailList);
        settleWageInfoMapper.updateByPrimaryKeySelective(settleWageInfo);
    }

    /**
     * 生成凭证实体
     * @return
     */
    private Voucher initVoucher(List<AccountTemp> accountTempList){
        Voucher voucher = CommonUtil.initVoucher((String) CommonUtil.getCurrentUser().get("id"));
        //业务日期
        voucher.setBizDate(new Date());
        //业务类型
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_SALARY_PAY);
        //交易类型
        voucher.setDealType(DICT.VOUCHER_DEAL_TYPE_OTHER);
        BigDecimal debitTotal = new BigDecimal("0");
        BigDecimal creditTotal = new BigDecimal("0");
        for (AccountTemp accountTemp:accountTempList){
            accountTemp.setVoucherId(voucher.getId());
            accountTemp.setId(CodeUtil.getId());
            if (accountTemp.getCreditAmt()!=null){
                creditTotal = creditTotal.add(accountTemp.getCreditAmt());
            }
            if (accountTemp.getDebitAmt() != null){
                debitTotal = debitTotal.add(accountTemp.getDebitAmt());
            }
        }
        //借方金额
        voucher.setDebitTotal(debitTotal);
        //贷方金额
        voucher.setCreditTotal(creditTotal);
        //审核人
        voucher.setAuditer((String) CommonUtil.getCurrentUser().get("id"));
        //备注
        voucher.setMemo("工资付款");
        return voucher;
    }
}
