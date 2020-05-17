package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.AssetsRegisterService;
import com.zhsnail.finance.util.SpringUtil;

public class AssetsRegisterReqListener extends BaseActivitiTaskEventListener {

    private AssetsRegisterService assetsRegisterService = SpringUtil.getBean(AssetsRegisterService.class);
    @Override
    public void approve(String businessKey) {

    }
    @Override
    public void lastApprove(String businessKey) {
        assetsRegisterService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        assetsRegisterService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        assetsRegisterService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
