package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.mapper.FloatWageMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.FloatWageVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FloatWageServiceImpl implements FloatWageService {
    @Autowired
    private FloatWageMapper floatWageMapper;

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
    private void transString(FloatWageVo floatWageVo){
        List<Account> allAccount = CommonUtil.findAllAccount();
        if (StringUtils.isNotBlank(floatWageVo.getCreditAccountId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(floatWageVo.getCreditAccountId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                floatWageVo.setCreditAccountName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        if (StringUtils.isNotBlank(floatWageVo.getDebitAccountId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(floatWageVo.getDebitAccountId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                floatWageVo.setDebitAccountName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        floatWageVo.setStateName(transMap.get(floatWageVo.getState()));
        floatWageVo.setTaxTypeName(transMap.get(floatWageVo.getTaxType()));
        floatWageVo.setSignTypeName(transMap.get(floatWageVo.getSignType()));
    }

    private List<FloatWageVo> transList(List<FloatWage> floatWageList){
        List<FloatWageVo> list = new ArrayList<>();
        floatWageList.forEach(item->{
            FloatWageVo floatWageVo = new FloatWageVo();
            BeanUtil.copyProperties(floatWageVo,item);
            transString(floatWageVo);
            list.add(floatWageVo);
        });
        return list;
    }

    @Override
    public void deleteFloatWage(String id) {
        floatWageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateFloatWage(FloatWageVo floatWageVo) {
        FloatWage floatWage = new FloatWage();
        BeanUtil.copyProperties(floatWage,floatWageVo);
        floatWageMapper.updateByPrimaryKeySelective(floatWage);
    }

    @Override
    public void saveFloatWage(FloatWageVo floatWageVo) {
        FloatWage floatWage = new FloatWage();
        BeanUtil.copyProperties(floatWage, floatWageVo);
        floatWage.setId(CodeUtil.getId());
        floatWageMapper.insert(floatWage);
    }

    @Override
    public PageInfo<FloatWageVo> findAll(FloatWageVo floatWageVo) {
        CommonUtil.startPage(floatWageVo);
        List<FloatWage> floatWageList = floatWageMapper.findAllByCondition(floatWageVo);
        long pageTotal = CommonUtil.getPageTotal(floatWageList);
        List<FloatWageVo> list = transList(floatWageList);
        PageInfo<FloatWageVo> floatWageVoPageInfo = new PageInfo<>(list);
        floatWageVoPageInfo.setTotal(pageTotal);
        return floatWageVoPageInfo;
    }

    @Override
    public List<FloatWageVo> exportFloatWageVo(FloatWageVo floatWageVo) {
        List<FloatWage> floatWageList = floatWageMapper.findAllByCondition(floatWageVo);
        return transList(floatWageList);
    }

    @Override
    public List<FloatWageVo> findBySignType(String signType) {
        List<FloatWage> floatWageList = floatWageMapper.findBySignType(signType);
        return transList(floatWageList);
    }

    @Override
    public Map<String, List<FloatWage>> generateFloatWageInfo() {
        FloatWageVo floatWageVo = new FloatWageVo();
        floatWageVo.setState(DICT.BOOLEAN_STATE_TRUE);
        List<FloatWage> allByCondition = floatWageMapper.findAllByCondition(floatWageVo);
        List<FloatWage> deductList = allByCondition.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_DEDUCT.equals(floatWage.getSignType())).collect(Collectors.toList());
        List<FloatWage> shouldPaidList = allByCondition.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID.equals(floatWage.getSignType())).collect(Collectors.toList());
        List<FloatWage> unitPayList = allByCondition.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_UNIT_PAY.equals(floatWage.getSignType())).collect(Collectors.toList());
        Map<String, List<FloatWage>> map = new HashMap<>();
        map.put("deductList",deductList);
        map.put("shouldPaidList",shouldPaidList);
        map.put("unitPayList",unitPayList);
        return map;
    }
}
