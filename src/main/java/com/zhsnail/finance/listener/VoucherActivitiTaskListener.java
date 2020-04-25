package com.zhsnail.finance.listener;

import org.activiti.engine.EngineServices;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.VariableInstance;

import java.util.Map;

public class VoucherActivitiTaskListener implements ExecutionListener {


    /*@Override
    public void notify(DelegateTask delegateTask) {
        String ProcessInstanceId = delegateTask.getProcessInstanceId();
        String assignee = delegateTask.getAssignee();
        Map<String, VariableInstance> variableInstances = delegateTask.getVariableInstances();
    }*/

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        String processBusinessKey = delegateExecution.getProcessBusinessKey();
        Map<String, VariableInstance> variableInstances = delegateExecution.getVariableInstances();
    }
}
