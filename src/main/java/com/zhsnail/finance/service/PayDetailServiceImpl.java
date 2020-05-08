package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.PayDetail;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.mapper.PayDetailMapper;
import com.zhsnail.finance.mapper.StudentInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.PayDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayDetailServiceImpl implements  PayDetailService {
    @Autowired
    private PayDetailMapper payDetailMapper;
    @Autowired
    private PayNoticeService payNoticeService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public PageInfo<PayDetailVo> findByPayNoticeId(PayDetailVo payDetailVo) {
        List<String> userIdList = new ArrayList<>();
        if (StringUtils.isNotBlank(payDetailVo.getUserName())){
            List<StudentInfo> studentInfo = studentInfoMapper.findByName(payDetailVo.getUserName());
            List<String> list = studentInfo.stream().map(item -> item.getId()).collect(Collectors.toList());
            userIdList.addAll(list);
        }
        payDetailVo.setUserIdList(userIdList);
        PageHelper.startPage(payDetailVo.getPageNum(),payDetailVo.getPageSize(),true);
        List<PayDetail> payDetailList = payDetailMapper.findAllByCondition(payDetailVo);
        List<PayDetailVo> payDetailVoList = new ArrayList<>();
        for (PayDetail payDetail : payDetailList){
            PayDetailVo temp = new PayDetailVo();
            BeanUtil.copyProperties(temp,payDetail);
            Map userInfo = CommonUtil.findUserInfo(payDetail.getUserId());
            temp.setUserName((String) userInfo.get("name"));
            payDetailVoList.add(temp);
        }
        PageInfo<PayDetailVo> payDetailVoPageInfo = new PageInfo<>(payDetailVoList);
        return payDetailVoPageInfo;
    }

    @Override
    public PayDetailVo findById(String id) {
        PayDetail payDetail = payDetailMapper.selectByPrimaryKey(id);
        PayDetailVo payDetailVo = new PayDetailVo();
        BeanUtil.copyProperties(payDetailVo,payDetail);
        Map userInfo = CommonUtil.findUserInfo(payDetail.getUserId());
        payDetailVo.setUserName((String) userInfo.get("name"));
        return payDetailVo;
    }

    @Override
    public void execPay(String id) {
        PayDetail payDetail = payDetailMapper.selectByPrimaryKey(id);
        payDetail.setPayDate(new Date());
        payDetail.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        payDetail.setUpdateTime(new Date());
        payDetail.setStatus(DICT.PAY_DETAIL_STATUS_PAID);
//        payDetailMapper.updateByPrimaryKeySelective(payDetail);
        String payNoticeId = payDetail.getPayNoticeId();
    }

    @Override
    public PageInfo<PayDetail> findByUserId(PageEntity pageEntity) {
        String id = (String) CommonUtil.getCurrentUser().get("id");
        PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),true);
        List<PayDetail> payDetailList = payDetailMapper.findByUserId(id);
        PageInfo<PayDetail> payDetailPageInfo = new PageInfo<>(payDetailList);
        return payDetailPageInfo;
    }
}
