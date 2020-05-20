package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.UpdateCache;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.*;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.StaffInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffInfoServiceImpl implements StaffInfoService {
    @Autowired
    private StaffInfoMapper staffInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;
    @Autowired
    private SettleWageInfoMapper settleWageInfoMapper;
    @Autowired
    private PayStubInfoMapper payStubInfoMapper;

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
    private void transString(StaffInfoVo staffInfoVo){
        if (staffInfoVo.getStationInfo() !=null){
            StationInfo stationInfo = staffInfoVo.getStationInfo();
            stationInfo.setType(transMap.get(stationInfo.getType()));
        }
    }

    private List<StaffInfoVo> transList(List<StaffInfo> staffInfoList){
        List<StaffInfoVo> list = new ArrayList<>();
        staffInfoList.forEach(item->{
            StaffInfoVo staffInfoVo = new StaffInfoVo();
            BeanUtil.copyProperties(staffInfoVo,item);
//            transString(staffInfoVo);
            list.add(staffInfoVo);
        });
        return list;
    }

    @Override
    @UpdateCache(name = "StaffInfoList",beanName = "staffInfoServiceImpl",methodName = "findAll")
    public void deleteStaffInfo(String id) {
        staffInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    @UpdateCache(name = "StaffInfoList",beanName = "staffInfoServiceImpl",methodName = "findAll")
    public void updateStaffInfo(StaffInfoVo staffInfoVo) {
        StaffInfo staffInfo = new StaffInfo();
        BeanUtil.copyProperties(staffInfo,staffInfoVo);
        StaffInfo oldStaffInfo = staffInfoMapper.selectByPrimaryKey(staffInfo.getId());
        if (!(oldStaffInfo.getName().equals(staffInfo.getName())) && StringUtils.isBlank(staffInfo.getUserName())){
            User user = userMapper.findByStaId(staffInfo.getId());
            user.setUserName(CommonUtil.toPinyin(staffInfo.getName(),false));
            userMapper.updateByPrimaryKeySelective(user);
        }
        if (!StringUtils.isBlank(staffInfo.getUserName())){
            User user = userMapper.findByStaId(staffInfo.getId());
            user.setUserName(CommonUtil.toPinyin(staffInfo.getName(),false));
            userMapper.updateByPrimaryKeySelective(user);
        }
        if (!(oldStaffInfo.getPayStubInfoId().equals(staffInfo.getPayStubInfoId()))){
            PayStubInfo oldPayStubInfo = payStubInfoMapper.selectByPrimaryKey(oldStaffInfo.getPayStubInfoId());
            SettleWageInfo oldSettleWageInfo = settleWageInfoMapper.selectByPrimaryKey(oldStaffInfo.getSettleWageId());
            oldPayStubInfo.setRelatedNumber(oldPayStubInfo.getRelatedNumber().subtract(new BigDecimal("1")));
            oldSettleWageInfo.setRelatedNumber(oldSettleWageInfo.getRelatedNumber().subtract(new BigDecimal("1")));
            PayStubInfo payStubInfo = payStubInfoMapper.selectByPrimaryKey(staffInfo.getPayStubInfoId());
            SettleWageInfo settleWageInfo = settleWageInfoMapper.findBySalPeriodAndPayStuId(CommonUtil.getNowSalaryPeriod(), staffInfo.getPayStubInfoId());
            payStubInfo.setRelatedNumber(payStubInfo.getRelatedNumber().add(new BigDecimal("1")));
            settleWageInfo.setRelatedNumber(settleWageInfo.getRelatedNumber().add(new BigDecimal("1")));
            payStubInfoMapper.updateByPrimaryKeySelective(oldPayStubInfo);
            payStubInfoMapper.updateByPrimaryKeySelective(payStubInfo);
            settleWageInfoMapper.updateByPrimaryKeySelective(settleWageInfo);
            settleWageInfoMapper.updateByPrimaryKeySelective(oldSettleWageInfo);
            staffInfo.setSettleWageId(settleWageInfo.getId());
        }
        bankInfoMapper.deleteByStaffId(staffInfo.getId());
        List<BankInfo> bankInfoList = staffInfoVo.getBankInfoList();
        bankInfoList.forEach(bankInfo -> {
            bankInfo.setStaffId(staffInfo.getId());
            bankInfo.setId(CodeUtil.getId());
        });
        bankInfoMapper.batchInsert(bankInfoList);
        staffInfoMapper.updateByPrimaryKeySelective(staffInfo);
    }

    @Override
    @UpdateCache(name = "StaffInfoList",beanName = "staffInfoServiceImpl",methodName = "findAll")
    public void saveStaffInfo(StaffInfoVo staffInfoVo) {
        StaffInfo staffInfo = new StaffInfo();
        BeanUtil.copyProperties(staffInfo, staffInfoVo);
        staffInfo.setId(CodeUtil.getId());
        List<BankInfo> bankInfoList = staffInfoVo.getBankInfoList();
        bankInfoList.forEach(bankInfo -> {
            bankInfo.setStaffId(staffInfo.getId());
            bankInfo.setId(CodeUtil.getId());
        });
        User user = new User();
        user.setId(CodeUtil.getId());
        user.setPsw("123456");
        user.setStaffId(staffInfo.getId());
        if (StringUtils.isBlank(staffInfo.getUserName())){
            user.setUserName(CommonUtil.toPinyin(staffInfo.getName(),false));
        }else {
            user.setUserName(staffInfo.getUserName());
        }
        String nowSalaryPeriod = CommonUtil.getNowSalaryPeriod();
        SettleWageInfo settleWageInfo = settleWageInfoMapper.findBySalPeriodAndPayStuId(nowSalaryPeriod, staffInfo.getPayStubInfoId());
        staffInfo.setSettleWageId(settleWageInfo.getId());
        settleWageInfo.setRelatedNumber(settleWageInfo.getRelatedNumber().add(new BigDecimal("1")));
        PayStubInfo payStubInfo = payStubInfoMapper.selectByPrimaryKey(staffInfo.getPayStubInfoId());
        payStubInfo.setRelatedNumber(payStubInfo.getRelatedNumber().add(new BigDecimal("1")));
        payStubInfoMapper.updateByPrimaryKeySelective(payStubInfo);
        settleWageInfoMapper.updateByPrimaryKeySelective(settleWageInfo);
        staffInfoMapper.insert(staffInfo);
        bankInfoMapper.batchInsert(bankInfoList);
        userMapper.insert(user);
    }

    @Override
    public PageInfo<StaffInfoVo> findAllByCondition(StaffInfoVo staffInfoVo) {
        CommonUtil.startPage(staffInfoVo);
        List<StaffInfo> staffInfoList = staffInfoMapper.findAllByCondition(staffInfoVo);
        long pageTotal = CommonUtil.getPageTotal(staffInfoList);
        List<StaffInfoVo> list = transList(staffInfoList);
        list.forEach(item->{
            item.getStationInfo().setType(transMap.get(item.getStationInfo().getType()));
        });
        PageInfo<StaffInfoVo> staffInfoVoPageInfo = new PageInfo<>(list);
        staffInfoVoPageInfo.setTotal(pageTotal);
        return staffInfoVoPageInfo;
    }

    @Override
    @Cacheable(value = "StaffInfoList")
    public List<StaffInfo> findAll() {
        List<StaffInfo> staffInfoList = staffInfoMapper.findAllByCondition(new StaffInfoVo());
        return staffInfoList;
    }

    @Override
    public StaffInfoVo findById(String id) {
        StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(id);
        StaffInfoVo staffInfoVo = new StaffInfoVo();
        BeanUtil.copyProperties(staffInfoVo,staffInfo);
        transString(staffInfoVo);
        return staffInfoVo;
    }
}
