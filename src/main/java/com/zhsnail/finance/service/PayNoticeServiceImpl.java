package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.mapper.PayNoticeMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayNoticeServiceImpl implements PayNoticeService {
    @Autowired
    private PayNoticeMapper payNoticeMapper;

    @Override
    public void saveOrUpdate(PayNoticeVo payNoticeVo) {
        PayNotice payNotice = new PayNotice();
        BeanUtil.copyProperties(payNotice,payNoticeVo);
        Map userInfo = (Map)SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        if (StringUtils.isNotBlank(payNoticeVo.getId())){
            payNotice.setUpdateTime(new Date());
            payNotice.setUpdater((String) userInfo.get("id"));
        }else {
            payNotice.setCreateTime(new Date());
            payNotice.setCreater((String) userInfo.get("id"));
        }
        List<Map> feeScopeList = payNoticeVo.getFeeScope();
        List<String> feeScopeIdList = feeScopeList.stream().map(map ->(String)map.get("id")).collect(Collectors.toList());
        payNotice.setFeeKindId((String) payNoticeVo.getFeeKind().get("id"));
        payNotice.setFeeScope(JsonUtil.obj2String(feeScopeIdList));
    }

    @Override
    public void commit(PayNoticeVo payNoticeVo) {

    }

    @Override
    public void deletePayNotice(String id) {

    }

    @Override
    public void updateStatusById(String id) {

    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public PayNotice findById(String id) {
        return null;
    }
}
