package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.FloatStub;
import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.entity.OrgInfo;
import com.zhsnail.finance.mapper.FloatStubMapper;
import com.zhsnail.finance.mapper.OrgInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.OrgInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrgInfoServiceImpl implements OrgInfoService {
    @Autowired
    private OrgInfoMapper orgInfoMapper;

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
    private void transString(OrgInfoVo orgInfoVo){
        orgInfoVo.setStateName(transMap.get(orgInfoVo.getState()));
    }

    private List<OrgInfoVo> transList(List<OrgInfo> orgInfoList){
        List<OrgInfoVo> list = new ArrayList<>();
        orgInfoList.forEach(item->{
            OrgInfoVo orgInfoVo = new OrgInfoVo();
            BeanUtil.copyProperties(orgInfoVo,item);
            transString(orgInfoVo);
            list.add(orgInfoVo);
        });
        return list;
    }

    @Override
    public void deleteOrgInfo(String id) {
        orgInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOrgInfo(OrgInfoVo orgInfoVo) {
        OrgInfo orgInfo = new OrgInfo();
        BeanUtil.copyProperties(orgInfo,orgInfoVo);
        orgInfoMapper.updateByPrimaryKeySelective(orgInfo);
    }

    @Override
    public void saveOrgInfo(OrgInfoVo orgInfoVo) {
        OrgInfo orgInfo = new OrgInfo();
        BeanUtil.copyProperties(orgInfo, orgInfoVo);
        orgInfo.setId(CodeUtil.getId());
        orgInfoMapper.insert(orgInfo);
    }

    @Override
    public PageInfo<OrgInfoVo> findAllByCondition(OrgInfoVo orgInfoVo) {
        CommonUtil.startPage(orgInfoVo);
        List<OrgInfo> orgInfoList = orgInfoMapper.findAllByCondition(orgInfoVo);
        long pageTotal = CommonUtil.getPageTotal(orgInfoList);
        List<OrgInfoVo> list = transList(orgInfoList);
        PageInfo<OrgInfoVo> orgInfoVoPageInfo = new PageInfo<>(list);
        orgInfoVoPageInfo.setTotal(pageTotal);
        return orgInfoVoPageInfo;
    }

    @Override
    public List<OrgInfoVo> findAll() {
        OrgInfoVo orgInfoVo = new OrgInfoVo();
        orgInfoVo.setState(DICT.BOOLEAN_STATE_TRUE);
        List<OrgInfo> orgInfoList = orgInfoMapper.findAllByCondition(orgInfoVo);
        return transList(orgInfoList);
    }
}
