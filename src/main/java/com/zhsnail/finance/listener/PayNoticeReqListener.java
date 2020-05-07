package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.PayNoticeService;
import com.zhsnail.finance.util.SpringUtil;

public class PayNoticeReqListener extends BaseActivitiTaskEventListener {
    private PayNoticeService payNoticeService = SpringUtil.getBean(PayNoticeService.class);
    @Override
    public void approve(String businessKey) {

    }

    @Override
    public void lastApprove(String businessKey) {
        payNoticeService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        payNoticeService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        payNoticeService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
