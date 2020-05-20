package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.ScaleSalaryMapper;
import com.zhsnail.finance.mapper.StationInfoMapper;
import com.zhsnail.finance.mapper.StationSalaryMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsChangeVo;
import com.zhsnail.finance.vo.StationInfoVo;
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
public class StationInfoServiceImpl implements StationInfoService {
    @Autowired
    private StationInfoMapper stationInfoMapper;
    @Autowired
    private ScaleSalaryMapper scaleSalaryMapper;
    @Autowired
    private StationSalaryMapper stationSalaryMapper;

    private static Map<String,String> transMap;

    static {
        transMap = new HashMap<>();
        transMap.put(DICT.BOOLEAN_STATE_TRUE,"启用");
        transMap.put(DICT.BOOLEAN_STATE_FALSE,"停用");
        transMap.put(DICT.STATION_INFO_TYPE_TCH,"教师类");
        transMap.put(DICT.STATION_INFO_TYPE_SEN,"高级管理类");
        transMap.put(DICT.STATION_INFO_TYPE_FUNC,"职能管理类");
        transMap.put(DICT.STATION_INFO_TYPE_OTH,"其他类");
    }
    private void transString(StationInfoVo stationInfoVo){
        List<Account> allAccount = CommonUtil.findAllAccount();
        if (StringUtils.isNotBlank(stationInfoVo.getCreditScaleAccId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(stationInfoVo.getCreditScaleAccId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                stationInfoVo.setCreditScaleAccName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        if (StringUtils.isNotBlank(stationInfoVo.getDebitScaleAccId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(stationInfoVo.getDebitScaleAccId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                stationInfoVo.setDebitScaleAccName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        if (StringUtils.isNotBlank(stationInfoVo.getCreditStationAccId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(stationInfoVo.getCreditStationAccId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                stationInfoVo.setCreditStationAccName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        if (StringUtils.isNotBlank(stationInfoVo.getDebitStationAccId())){
            List<Account> collect = allAccount.stream().filter(account -> account.getId().equals(stationInfoVo.getDebitStationAccId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                stationInfoVo.setDebitStationAccName(CommonUtil.getAccountLongName(collect.get(0)));
            }
        }
        stationInfoVo.setStateName(transMap.get(stationInfoVo.getState()));
        stationInfoVo.setTypeName(transMap.get(stationInfoVo.getType()));
    }

    private List<StationInfoVo> transList(List<StationInfo> stationInfoList){
        List<StationInfoVo> list = new ArrayList<>();
        stationInfoList.forEach(item->{
            StationInfoVo stationInfoVo = new StationInfoVo();
            BeanUtil.copyProperties(stationInfoVo,item);
            transString(stationInfoVo);
            list.add(stationInfoVo);
        });
        return list;
    }
    @Override
    public void deleteStationInfo(String id) {
        stationInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStationInfo(StationInfoVo stationInfoVo) {
        StationInfo stationInfo = new StationInfo();
        BeanUtil.copyProperties(stationInfo,stationInfoVo);
        stationInfoMapper.updateByPrimaryKeySelective(stationInfo);
    }

    @Override
    public void saveStationInfo(StationInfoVo stationInfoVo) {
        StationInfo stationInfo = new StationInfo();
        BeanUtil.copyProperties(stationInfo, stationInfoVo);
        stationInfo.setId(CodeUtil.getId());
        StationSalary stationSalary = stationSalaryMapper.findByType(stationInfo.getType());
        ScaleSalary scaleSalary = scaleSalaryMapper.findByType(stationInfo.getType());
        stationInfo.setScaleSalaryId(scaleSalary.getId());
        stationInfo.setStationSalaryId(stationSalary.getId());
        stationInfoMapper.insert(stationInfo);
    }

    @Override
    public PageInfo<StationInfoVo> findAllByCondition(StationInfoVo stationInfoVo) {
        CommonUtil.startPage(stationInfoVo);
        List<StationInfo> stationInfoList = stationInfoMapper.findAllByCondition(stationInfoVo);
        long pageTotal = CommonUtil.getPageTotal(stationInfoList);
        List<StationInfoVo> list = transList(stationInfoList);
        PageInfo<StationInfoVo> stationInfoVoPageInfo = new PageInfo<>(list);
        stationInfoVoPageInfo.setTotal(pageTotal);
        return stationInfoVoPageInfo;
    }



    @Override
    public List<Map> generateStationInfo() {
        List<StationInfo> stationInfoList = stationInfoMapper.findAllByCondition(new StationInfoVo());
        ArrayList<Map> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<Map> findScaleAndStationList() {
        List<ScaleSalary> scaleSalaryList = scaleSalaryMapper.findAll();
        List<StationSalary> stationSalaryList = stationSalaryMapper.findAll();
        List<Map> mapList = new ArrayList<>();
        for (ScaleSalary scaleSalary : scaleSalaryList){
            HashMap<String, Object> temp = new HashMap<>();
            String type = scaleSalary.getType();
            temp.putAll(BeanUtil.beanToMap(scaleSalary));
            List<Map<String, Object>> collect = stationSalaryList.stream().filter(stationSalary -> type.equals(stationSalary.getType())).map(item -> BeanUtil.beanToMap(item)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)){
                temp.putAll(collect.get(0));
            }
            temp.put("type",transMap.get(type));
            mapList.add(temp);
        }
        return mapList;
    }

    @Override
    public void updateScaleAndStation(StationInfoVo stationInfoVo) {
        ScaleSalary scaleSalary = stationInfoVo.getScaleSalary();
        StationSalary stationSalary = stationInfoVo.getStationSalary();
        scaleSalary.setType(CommonUtil.getKeyByVal(transMap,scaleSalary.getType()));
        stationSalary.setType(CommonUtil.getKeyByVal(transMap,stationSalary.getType()));
        scaleSalaryMapper.updateByPrimaryKeySelective(scaleSalary);
        stationSalaryMapper.updateByPrimaryKeySelective(stationSalary);
    }
    @Override
    public List<StationInfoVo> exportStationInfoVo(StationInfoVo stationInfoVo) {
        List<StationInfo> stationInfoList = stationInfoMapper.findAllByCondition(stationInfoVo);
        return transList(stationInfoList);
    }

    @Override
    public List<StationInfoVo> findAll() {
        StationInfoVo stationInfoVo = new StationInfoVo();
        stationInfoVo.setState(DICT.BOOLEAN_STATE_TRUE);
        List<StationInfo> allByCondition = stationInfoMapper.findAllByCondition(stationInfoVo);
        return transList(allByCondition);
    }
}
