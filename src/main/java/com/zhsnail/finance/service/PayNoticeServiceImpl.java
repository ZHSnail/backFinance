package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.mapper.PayDetailMapper;
import com.zhsnail.finance.mapper.PayNoticeMapper;
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
        Map account = (Map) payNoticeVo.getFeeKindList().get("account");
        payNotice.setAccountId((String) account.get("id"));
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
        Map currentUser = CommonUtil.getCurrentUser();
        List<Role> roles = (List<Role>) currentUser.get("roles");
        List<String> ids = new ArrayList<>();
        for (Role role : roles){
            List<String> bizIdList = activityService.findCmtTask(DICT.PAY_NOTICE_WORK_KEY, role.getId());
            ids.addAll(bizIdList);
        }
        ids = ids.stream().distinct().collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(ids)){
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),true);
            List<PayNotice> payNoticeList = payNoticeMapper.findByIds(ids);
            PageInfo<PayNotice> pageInfo = new PageInfo<>(payNoticeList);
            return pageInfo;
        }
        return new PageInfo<>(new ArrayList<PayNotice>());
    }
}
