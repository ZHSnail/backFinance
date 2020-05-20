package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.entity.OrgInfo;
import com.zhsnail.finance.entity.SettleWageDetail;
import com.zhsnail.finance.mapper.SettleWageDetailMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.SettleWageDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SettleWageDetailServiceImpl implements SettleWageDetailService {

    @Autowired
    private SettleWageDetailMapper settleWageDetailMapper;


    @Override
    public PageInfo<Map> findAllByCondition(SettleWageDetailVo settleWageDetailVo) {
        CommonUtil.startPage(settleWageDetailVo);
        List<SettleWageDetail> allByCondition = settleWageDetailMapper.findAllByCondition(settleWageDetailVo);
        long pageTotal = CommonUtil.getPageTotal(allByCondition);
        List<Map> mapList = new ArrayList<>();
        allByCondition.forEach(item->{
            Map<String, Object> map = BeanUtil.beanToMap(item);
            List<FloatWage> floatWageList = item.getStaffInfo().getPayStubInfo().getFloatWageList();
            List<FloatWage> shouldPaidList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID.equals(floatWage.getSignType())).collect(Collectors.toList());
            List<FloatWage> deductList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_DEDUCT.equals(floatWage.getSignType())).collect(Collectors.toList());
            List<FloatWage> untiPayList = floatWageList.stream().filter(floatWage -> DICT.FLOAT_WAGE_SIGN_TYPE_UNIT_PAY.equals(floatWage.getSignType())).collect(Collectors.toList());
            map.put("shouldPaidList",shouldPaidList);
            map.put("deductList",deductList);
            map.put("untiPayList",untiPayList);
            mapList.add(map);
        });
        PageInfo<Map> mapPageInfo = new PageInfo<>(mapList);
        return mapPageInfo;
    }
}
