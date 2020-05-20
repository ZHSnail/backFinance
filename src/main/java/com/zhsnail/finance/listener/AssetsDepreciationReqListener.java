package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.AssetsDepreciationService;
import com.zhsnail.finance.util.SpringUtil;

public class AssetsDepreciationReqListener extends BaseActivitiTaskEventListener {

    private AssetsDepreciationService assetsDepreciationService = SpringUtil.getBean(AssetsDepreciationService.class);
    @Override
    public void approve(String businessKey) {

    }
    @Override
    public void lastApprove(String businessKey) {
        assetsDepreciationService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        assetsDepreciationService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        assetsDepreciationService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
