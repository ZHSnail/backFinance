package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.AssetsChangeService;
import com.zhsnail.finance.util.SpringUtil;

public class AssetsChangeReqListener extends BaseActivitiTaskEventListener {

    private AssetsChangeService assetsChangeService = SpringUtil.getBean(AssetsChangeService.class);
    @Override
    public void approve(String businessKey) {

    }
    @Override
    public void lastApprove(String businessKey) {
        assetsChangeService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        assetsChangeService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        assetsChangeService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
