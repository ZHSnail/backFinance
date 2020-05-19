package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.AssetsPurchaseService;
import com.zhsnail.finance.util.SpringUtil;

public class AssetsPurchaseReqListener extends BaseActivitiTaskEventListener {

    private AssetsPurchaseService assetsPurchaseService = SpringUtil.getBean(AssetsPurchaseService.class);
    @Override
    public void approve(String businessKey) {

    }
    @Override
    public void lastApprove(String businessKey) {
        assetsPurchaseService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        assetsPurchaseService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        assetsPurchaseService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
