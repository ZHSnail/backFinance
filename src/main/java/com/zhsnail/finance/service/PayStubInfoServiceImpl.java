package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.FloatStubMapper;
import com.zhsnail.finance.mapper.PayStubInfoMapper;
import com.zhsnail.finance.mapper.SettleWageInfoMapper;
import com.zhsnail.finance.mapper.StaffInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.PayStubInfoVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayStubInfoServiceImpl implements PayStubInfoService {
    @Autowired
    private PayStubInfoMapper payStubInfoMapper;
    @Autowired
    private FloatStubMapper floatStubMapper;
    @Autowired
    private SettleWageInfoMapper settleWageInfoMapper;
    @Autowired
    private StaffInfoMapper staffInfoMapper;

    private static Map<String,String> transMap;

    static {
        transMap = new HashMap<>();
        transMap.put(DICT.BOOLEAN_STATE_TRUE,"启用");
        transMap.put(DICT.BOOLEAN_STATE_FALSE,"停用");
        transMap.put(DICT.STATION_INFO_TYPE_TCH,"教师类");
        transMap.put(DICT.STATION_INFO_TYPE_SEN,"高级管理类");
        transMap.put(DICT.STATION_INFO_TYPE_FUNC,"职能管理类");
        transMap.put(DICT.STATION_INFO_TYPE_OTH,"其他类");
        transMap.put(DICT.FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID,"应发项");
        transMap.put(DICT.FLOAT_WAGE_SIGN_TYPE_DEDUCT,"扣减项");
        transMap.put(DICT.FLOAT_WAGE_SIGN_TYPE_UNIT_PAY,"单位缴纳");
        transMap.put(DICT.FLOAT_WAGE_TAX_TYPE_TAX,"应税项");
        transMap.put(DICT.FLOAT_WAGE_TAX_TYPE_NON_TAX,"非税项");
        transMap.put(DICT.FLOAT_WAGE_TAX_TYPE_PRE_TAX_DED,"税前扣减项");
    }
    private void transString(PayStubInfoVo payStubInfoVo){
        payStubInfoVo.setScopeName(transMap.get(payStubInfoVo.getScope()));
        List<FloatWage> floatWageList = payStubInfoVo.getFloatWageList();
        List<FloatWage> deductFloatWage = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_DEDUCT.equals(floatWage.getSignType())).collect(Collectors.toList());
        List<FloatWage> shouldPaidFloatWage = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID.equals(floatWage.getSignType())).collect(Collectors.toList());
        List<FloatWage> unitPayFloatWage = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_UNIT_PAY.equals(floatWage.getSignType())).collect(Collectors.toList());
        List<String> deductList = deductFloatWage.stream().map(floatWage -> floatWage.getId()).collect(Collectors.toList());
        List<String> shouldPaidList = shouldPaidFloatWage.stream().map(floatWage -> floatWage.getId()).collect(Collectors.toList());
        List<String> unitPayList = unitPayFloatWage.stream().map(floatWage -> floatWage.getId()).collect(Collectors.toList());
        payStubInfoVo.setDeductList(deductList);
        payStubInfoVo.setShouldPaidList(shouldPaidList);
        payStubInfoVo.setUnitPayList(unitPayList);
        StringBuffer sb = new StringBuffer();
        deductFloatWage.forEach(floatWage -> {
            sb.append(floatWage.getName());
            sb.append(",");
        });
        sb.delete(sb.lastIndexOf(","),sb.length());
        payStubInfoVo.setDeduct(sb.toString());
        sb.setLength(0);
        shouldPaidFloatWage.forEach(floatWage -> {
            sb.append(floatWage.getName());
            sb.append(",");
        });
        sb.delete(sb.lastIndexOf(","),sb.length());
        payStubInfoVo.setShouldPaid(sb.toString());
        sb.setLength(0);
        shouldPaidFloatWage.forEach(floatWage -> {
            sb.append(floatWage.getName());
            sb.append(",");
        });
        sb.delete(sb.lastIndexOf(","),sb.length());
        payStubInfoVo.setUnitPay(sb.toString());
        sb.setLength(0);
    }

    private List<PayStubInfoVo> transList(List<PayStubInfo> payStubInfoList){
        List<PayStubInfoVo> list = new ArrayList<>();
        payStubInfoList.forEach(item->{
            PayStubInfoVo payStubInfoVo = new PayStubInfoVo();
            BeanUtil.copyProperties(payStubInfoVo,item);
            transString(payStubInfoVo);
            list.add(payStubInfoVo);
        });
        return list;
    }

    @Override
    public void deletePayStubInfo(String id) {
        payStubInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePayStubInfo(PayStubInfoVo payStubInfoVo) {
        PayStubInfo payStubInfo = new PayStubInfo();
        BeanUtil.copyProperties(payStubInfo,payStubInfoVo);
        List<StaffInfo> staffInfoList = staffInfoMapper.findByPayStubInfoId(payStubInfo.getId());
        if (CollectionUtils.isNotEmpty(staffInfoList)){
            throw new BaseRuningTimeException("当前工资单有员工关联，无法修改！");
        }else {
            List<FloatStub> floatStubList = initFloatStub(payStubInfoVo, payStubInfo.getId());
            floatStubMapper.deleteByPayStubInfoId(payStubInfo.getId());
            floatStubMapper.batchInsert(floatStubList);
            payStubInfoMapper.updateByPrimaryKeySelective(payStubInfo);
        }
    }

    private List<FloatStub> initFloatStub(PayStubInfoVo payStubInfoVo,String payStubInfoId){
        List<String> deductList = payStubInfoVo.getDeductList();
        List<String> unitPayList = payStubInfoVo.getUnitPayList();
        List<String> shouldPaidList = payStubInfoVo.getShouldPaidList();
        List<String> floatWageIds = new ArrayList<>();
        floatWageIds.addAll(deductList);
        floatWageIds.addAll(unitPayList);
        floatWageIds.addAll(shouldPaidList);
        List<FloatStub> floatStubList = new ArrayList<>();
        floatWageIds.forEach(floatWageId->{
            FloatStub floatStub = new FloatStub();
            floatStub.setFloatWageId(floatWageId);
            floatStub.setPayStubInfoId(payStubInfoId);
            floatStub.setId(CodeUtil.getId());
            floatStubList.add(floatStub);
        });
        return floatStubList;
    }

    private List<SettleWageInfo> initSettleWageInfoList(String payStubInfoId,String name){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String dateString = format.format(date);
        List<SettleWageInfo> settleWageInfoList = new ArrayList<>();
        for (int i=1;i<=12;i++){
            SettleWageInfo settleWageInfo = new SettleWageInfo();
            settleWageInfo.setId(CodeUtil.getId());
            settleWageInfo.setPayStubInfoId(payStubInfoId);
            settleWageInfo.setName(name);
            settleWageInfo.setRelatedNumber(new BigDecimal("0"));
            if (i<10){
                settleWageInfo.setSalaryPeriod(dateString+"0"+i);
            }else {
                settleWageInfo.setSalaryPeriod(dateString+i);
            }
            settleWageInfoList.add(settleWageInfo);
        }
        return settleWageInfoList;
    }
    @Override
    public void savePayStubInfo(PayStubInfoVo payStubInfoVo) {
        PayStubInfo payStubInfo = new PayStubInfo();
        BeanUtil.copyProperties(payStubInfo, payStubInfoVo);
        payStubInfo.setRelatedNumber(new BigDecimal("0"));
        payStubInfo.setId(CodeUtil.getId());
        List<FloatStub> floatStubList = initFloatStub(payStubInfoVo, payStubInfo.getId());
        List<SettleWageInfo> settleWageInfoList = initSettleWageInfoList(payStubInfo.getId(), payStubInfo.getName());
        floatStubMapper.batchInsert(floatStubList);
        settleWageInfoMapper.batchInsert(settleWageInfoList);
        payStubInfoMapper.insert(payStubInfo);
    }

    @Override
    public PageInfo<PayStubInfoVo> findAllByCondition(PayStubInfoVo payStubInfoVo) {
        CommonUtil.startPage(payStubInfoVo);
        List<PayStubInfo> payStubInfoList = payStubInfoMapper.findAllByCondition(payStubInfoVo);
        long pageTotal = CommonUtil.getPageTotal(payStubInfoList);
        List<PayStubInfoVo> list = transList(payStubInfoList);
        PageInfo<PayStubInfoVo> payStubInfoVoPageInfo = new PageInfo<>(list);
        payStubInfoVoPageInfo.setTotal(pageTotal);
        return payStubInfoVoPageInfo;
    }

    @Override
    public List<PayStubInfoVo> findAll() {
        List<PayStubInfo> payStubInfoList = payStubInfoMapper.findAllByCondition(new PayStubInfoVo());
        return transList(payStubInfoList);
    }
}
