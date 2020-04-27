/*
package com.zhsnail.finance.controller;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class test{
    public static InputStream getFlowImgByInstantId(String processInstanceId) {
        if (StringUtils.isEmpty(processInstanceId)) {
            return null;
        }
        // 获取流程图输入流
        InputStream inputStream = null;
        // 查询历史
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (historicProcessInstance.getEndTime() != null) { // 该流程已经结束
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(historicProcessInstance.getProcessDefinitionId())
                    .singleResult();
            inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        } else {
            // 查询当前的流程实例
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
            List<String> highLightedFlows = new ArrayList<String>();
            List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                    .orderByHistoricActivityInstanceStartTime().asc().list();
            List<String> historicActivityInstanceList = new ArrayList<String>();
            for (HistoricActivityInstance hai : historicActivityInstances) {
                historicActivityInstanceList.add(hai.getActivityId());
            }
            List<String> highLightedActivities = runtimeService.getActiveActivityIds(processInstanceId);
            historicActivityInstanceList.addAll(highLightedActivities);
            for (ActivityImpl activity : processDefinitionEntity.getActivities()) {
                int index = historicActivityInstanceList.indexOf(activity.getId());
                if (index >= 0 && index + 1 < historicActivityInstanceList.size()) {
                    List<PvmTransition> pvmTransitionList = activity.getOutgoingTransitions();
                    for (PvmTransition pvmTransition : pvmTransitionList) {
                        String destinationFlowId = pvmTransition.getDestination().getId();
                        if (destinationFlowId.equals(historicActivityInstanceList.get(index + 1))) {
                            highLightedFlows.add(pvmTransition.getId());
                        }
                    }
                }
            }
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            List<String> activeActivityIds = new ArrayList<String>();
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
            for (Task task : tasks) {
                activeActivityIds.add(task.getTaskDefinitionKey());
            }
            inputStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, highLightedFlows, "宋体", "宋体", null, null, 1.0);
        }
        return inputStream;
    }
}*/
