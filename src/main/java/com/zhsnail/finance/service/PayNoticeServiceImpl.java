package com.zhsnail.finance.service;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.mapper.PayDetailMapper;
import com.zhsnail.finance.mapper.PayNoticeMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
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
    @Autowired
    private PayDetailMapper payDetailMapper;

    @Override
    public void saveOrUpdate(PayNoticeVo payNoticeVo) {
        PayNotice payNotice = new PayNotice();
        BeanUtil.copyProperties(payNotice,payNoticeVo);
        Map userInfo = (Map)SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        List<Map> feeScopeList = payNoticeVo.getFeeScope();
        List<String> feeScopeIdList = feeScopeList.stream().map(map ->(String)map.get("id")).collect(Collectors.toList());
        payNotice.setFeeKindId((String) payNoticeVo.getFeeKindList().get("id"));
        payNotice.setFeeScope(JsonUtil.obj2String(feeScopeIdList));
        payNotice.setCode(CodeUtil.getId());
        Map account = (Map) payNoticeVo.getFeeKindList().get("account");
        payNotice.setAccountId((String) account.get("id"));
        payNotice.setStatus(DICT.STATUS_DRAFT);
        if (StringUtils.isNotBlank(payNoticeVo.getId())){
            payNotice.setUpdateTime(new Date());
            payNotice.setUpdater((String) userInfo.get("id"));
            payNoticeMapper.updateByPrimaryKeySelective(payNotice);
        }else {
            payNotice.setCreateTime(new Date());
            payNotice.setCreater((String) userInfo.get("id"));
            payNotice.setId(CodeUtil.getId());
            payNoticeMapper.insert(payNotice);
        }
    }

    @Override
    public void commit(PayNoticeVo payNoticeVo) {

    }

    @Override
    public void deletePayNotice(String id) {

    }

    @Override
    public void updateStatusById(String id,String status) {

    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public Map findById(String id) {
        return null;
    }

    @Override
    public List<Map> findTaskList() {
        return null;
    }
}
