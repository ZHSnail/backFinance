package com.zhsnail.finance.listener;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.service.VoucherService;
import com.zhsnail.finance.util.SpringUtil;
import org.activiti.engine.EngineServices;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.VariableInstance;

import java.util.Map;

public class VoucherReqListener extends BaseActivitiTaskEventListener {

    private VoucherService voucherService = SpringUtil.getBean(VoucherService.class);
    @Override
    public void approve(String businessKey) {

    }
    @Override
    public void lastApprove(String businessKey) {
        voucherService.lastApprove(businessKey);
    }

    @Override
    public void refuse(String businessKey) {
        voucherService.updateStatusById(businessKey, DICT.STATUS_BACK);
    }

    @Override
    public void apply(String businessKey) {

    }

    @Override
    public void revoke(String businessKey) {
        voucherService.updateStatusById(businessKey,DICT.STATUS_BACK);
    }
}
