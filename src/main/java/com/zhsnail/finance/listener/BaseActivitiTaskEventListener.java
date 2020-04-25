package com.zhsnail.finance.listener;


import com.github.pagehelper.util.StringUtil;
import com.zhsnail.finance.common.DICT;
import org.activiti.engine.delegate.event.*;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.IdentityLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseActivitiTaskEventListener implements ActivitiEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseActivitiTaskEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        String activityId = "";
        TaskEntity task=new TaskEntity();
        Map variables=new ConcurrentHashMap();
        List<String> defaultUserList = new ArrayList<String>();
        List<String> defaultRoleList = new ArrayList<String>();
        //任务完成
        if (event.getType().equals(ActivitiEventType.TASK_COMPLETED)) {
            if(event instanceof ActivitiEntityWithVariablesEvent){
                ActivitiEntityWithVariablesEvent variablesEvent =  (ActivitiEntityWithVariablesEvent)event;
                // 获取流程参数
                variables = variablesEvent.getVariables();
            }
            if(event instanceof ActivitiEntityEvent){
                ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
                task = (TaskEntity) entityEvent.getEntity();
                activityId = task.getTaskDefinitionKey();
                variables.put(DICT.TASK_DEF_KEY, activityId);

                Set<IdentityLink> identityLinks = task.getCandidates();
                Iterator<IdentityLink> it = identityLinks.iterator();
                while(it.hasNext()){
                    IdentityLink identityLink = it.next();
                    if(StringUtil.isNotEmpty(identityLink.getUserId())){
                        defaultUserList.add(identityLink.getUserId());
                    }
                    if(StringUtil.isNotEmpty(identityLink.getGroupId())){
                        defaultRoleList.add(identityLink.getGroupId());
                    }
                }
                String processDefinitionId = task.getProcessDefinitionId();
            }
            // 操作
            String action = (String) variables.get(DICT.ACTION);
            // 业务ID
            String businessKey = (String) variables.get(DICT.BUSINESSKEY);
            if(variables == null){
                variables=new ConcurrentHashMap();
            }
            LOGGER.info("TASK_COMPLETED: action: " + action + " businessKey:" + businessKey);
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    public abstract void approve(String businessKey) ;

    public abstract void lastApprove(String businessKey);
}
