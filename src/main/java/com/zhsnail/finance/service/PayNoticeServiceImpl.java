package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.PayDetailMapper;
import com.zhsnail.finance.mapper.PayNoticeMapper;
import com.zhsnail.finance.mapper.StudentInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayNoticeServiceImpl implements PayNoticeService {
    @Autowired
    private PayNoticeMapper payNoticeMapper;
    @Autowired
    private PayDetailMapper payDetailMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public void saveOrUpdate(PayNoticeVo payNoticeVo) {
        PayNotice payNotice = init(payNoticeVo);
        payNotice.setStatus(DICT.STATUS_DRAFT);
        if (StringUtils.isNotBlank(payNotice.getId())){
            payNoticeMapper.updateByPrimaryKeySelective(payNotice);
        }else {
            payNotice.setCode(CodeUtil.getCode("SF"));
            payNotice.setId(CodeUtil.getId());
            payNoticeMapper.insert(payNotice);
        }
    }

    private PayNotice init(PayNoticeVo payNoticeVo){
        PayNotice payNotice = new PayNotice();
        BeanUtil.copyProperties(payNotice,payNoticeVo);
        List<Map> feeScopeList = payNoticeVo.getFeeScope();
        List<String> feeScopeIdList = feeScopeList.stream().map(map ->(String)map.get("id")).collect(Collectors.toList());
        payNotice.setFeeKindId((String) payNoticeVo.getFeeKindList().get("id"));
        payNotice.setFeeScope(JsonUtil.obj2String(feeScopeIdList));
        Map userInfo = (Map)SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        if (StringUtils.isNotBlank(payNoticeVo.getId())){
            payNotice.setUpdateTime(new Date());
            payNotice.setUpdater((String) userInfo.get("id"));
        }
        payNotice.setCreateTime(new Date());
        payNotice.setCreater((String) userInfo.get("id"));
        return payNotice;
    }
    @Override
    public void commit(PayNoticeVo payNoticeVo) {
        PayNotice payNotice = init(payNoticeVo);
        payNotice.setStatus(DICT.STATUS_CMT);
        if (StringUtils.isNotBlank(payNotice.getId())){
            payNoticeMapper.updateByPrimaryKeySelective(payNotice);
        }else {
            payNotice.setCode(CodeUtil.getCode("SF"));
            payNotice.setId(CodeUtil.getId());
            payNoticeMapper.insert(payNotice);
        }
        //开启工作流
        activityService.runStart(DICT.PAY_NOTICE_WORK_KEY,payNotice.getId(),new HashMap());
    }

    @Override
    public void deletePayNotice(String id) {
        payNoticeMapper.deleteByPrimaryKey(id);
        activityService.deleteFlow(DICT.PAY_NOTICE_WORK_KEY,id);
    }

    @Override
    public void updateStatusById(String id,String status) {
        PayNotice payNotice = payNoticeMapper.selectByPrimaryKey(id);
        payNotice.setStatus(status);
        payNoticeMapper.updateByPrimaryKeySelective(payNotice);
    }

    @Override
    public void lastApprove(String id) {
        PayNotice payNotice = payNoticeMapper.selectByPrimaryKey(id);
        payNotice.setStatus(DICT.STATUS_EXE);
        FeeKind feeKind = payNotice.getFeeKind();
        String feeScope = payNotice.getFeeScope();
        List feeScopeList = JsonUtil.getListByJsonArray(feeScope);
        List<StudentInfo> studentInfoList = new ArrayList<>();
        if (DICT.FEE_METHOD_DORM.equals(feeKind.getFeeMethod())){
            if (CollectionUtils.isNotEmpty(feeScopeList)){
                studentInfoList = studentInfoMapper.findByDormIds(feeScopeList);
            }
        }
        if (DICT.FEE_METHOD_MAJOR.equals(feeKind.getFeeMethod())){
            if (CollectionUtils.isNotEmpty(feeScopeList)){
                studentInfoList = studentInfoMapper.findByProfessionIds(feeScopeList);
            }
        }
        if (CollectionUtils.isNotEmpty(studentInfoList)){
            List<PayDetail> payDetailList = new ArrayList<>();
            for (StudentInfo studentInfo : studentInfoList){
                PayDetail payDetail = new PayDetail();
                payDetail.setId(CodeUtil.getId());
                payDetail.setAmount(payNotice.getAmount());
                payDetail.setCode(CodeUtil.getCode(CommonUtil.toPinyin(feeKind.getName(),true)));
                payDetail.setStatus(DICT.PAY_DETAIL_STATUS_UNPAID);
                payDetail.setFeeMethod(feeKind.getFeeMethod());
                payDetail.setPayNoticeId(payNotice.getId());
                payDetail.setUserId(studentInfo.getId());
                payDetail.setMemo(payNotice.getMemo());
                payDetail.setCreater((String) CommonUtil.getCurrentUser().get("id"));
                payDetail.setCreateTime(new Date());
                payDetailList.add(payDetail);
            }
            if (CollectionUtils.isNotEmpty(payDetailList)){
                payDetailMapper.batchInsert(payDetailList);
            }
        }
        payNoticeMapper.updateByPrimaryKeySelective(payNotice);
    }

    @Override
    public Map findById(String id) {
        PayNotice payNotice = payNoticeMapper.selectByPrimaryKey(id);
        return BeanUtil.beanToMap(payNotice);
    }

    @Override
    public Map findTaskMapList() {
        PayNoticeVo payNoticeVo = new PayNoticeVo();
        payNoticeVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        List<PayNotice> payNoticeList = payNoticeMapper.findTaskList(payNoticeVo);
        //草稿
        List<PayNotice> draftList;
        //审核中的列表
        List<PayNotice> cmtList;
        //正在执行的列表
        List<PayNotice> exeList;
        //已经完成的列表
        List<PayNotice> finishList = new ArrayList<>();
        draftList = payNoticeList.stream().filter(payNotice -> DICT.STATUS_BACK.equals(payNotice.getStatus()) ||
                DICT.STATUS_DRAFT.equals(payNotice.getStatus())).collect(Collectors.toList());
        draftList.forEach(item->{
            Map userInfo = CommonUtil.findUserInfo(item.getCreater());
            item.setCreater((String) userInfo.get("name"));
            item.setStatus("草稿");
        });
        cmtList = payNoticeList.stream().filter(payNotice -> DICT.STATUS_CMT.equals(payNotice.getStatus())).collect(Collectors.toList());
        cmtList.forEach(item->{
            Map userInfo = CommonUtil.findUserInfo(item.getCreater());
            item.setCreater((String) userInfo.get("name"));
            item.setStatus("审核中");
        });
        exeList = payNoticeList.stream().filter(payNotice -> DICT.STATUS_EXE.equals(payNotice.getStatus())).collect(Collectors.toList());
        exeList.forEach(item->{
            Map userInfo = CommonUtil.findUserInfo(item.getCreater());
            item.setCreater((String) userInfo.get("name"));
            item.setStatus("正在进行收款");
        });
        Map map = new HashMap<>();
        map.put("draftList",draftList);
        map.put("cmtList",cmtList);
        map.put("exeList",exeList);
        map.put("finishList",finishList);
        return map;
    }

    @Override
    public PageInfo<PayNotice> findCmtTaskList(PageEntity pageEntity) {
        List<String> ids = activityService.findCmtBizIds(DICT.PAY_NOTICE_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)){
            CommonUtil.startPage(pageEntity);
            List<PayNotice> payNoticeList = payNoticeMapper.findByIds(ids);
            PageInfo<PayNotice> pageInfo = new PageInfo<>(payNoticeList);
            return pageInfo;
        }
        return new PageInfo<>(new ArrayList<PayNotice>());
    }

    @Override
    public PageInfo<PayNotice> findListByCondition(PayNoticeVo payNoticeVo) {
        CommonUtil.startPage(payNoticeVo);
        List<PayNotice> payNoticeList = payNoticeMapper.findAllByCondition(payNoticeVo);
        payNoticeList.forEach(payNotice -> {
            String name = (String) CommonUtil.findUserInfo(payNotice.getCreater()).get("name");
            payNotice.setCreater(name);
        });
        PageInfo<PayNotice> payNoticePageInfo = new PageInfo<>(payNoticeList);
        return payNoticePageInfo;
    }

    @Override
    public List<PayNoticeVo> exportPayNoticeVo(PayNoticeVo payNoticeVo) {
        List<PayNotice> payNoticeList = payNoticeMapper.findAllByCondition(payNoticeVo);
        List<PayNoticeVo> payNoticeVoList = new ArrayList<>();
        for (PayNotice payNotice : payNoticeList){
            PayNoticeVo temp = new PayNoticeVo();
            Map<String, Object> map = BeanUtil.beanToMap(payNotice);
            map.put("feeScope",new ArrayList<>());
            BeanUtil.copyProperties(temp,map);
            String name = (String) CommonUtil.findUserInfo(payNotice.getCreater()).get("name");
            temp.setCreater(name);
            temp.setFeeKindId(payNotice.getFeeKind().getName());
            if (DICT.STATUS_EXE.equals(payNotice.getStatus())){
                temp.setStatus("正在进行收费");
            }
            if (DICT.STATUS_FINSH.equals(payNotice.getStatus())){
                temp.setStatus("收费已完成");
            }
            if (DICT.STATUS_CMT.equals(payNotice.getStatus())){
                temp.setStatus("待审核通过");
            }
            temp.setDebitAccount(payNotice.getFeeKind().getDebitAccount().getAccountName());
            temp.setCreditAccount(payNotice.getFeeKind().getCreditAccount().getAccountName());
            payNoticeVoList.add(temp);
        }
        return payNoticeVoList;
    }
}
