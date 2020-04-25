package com.zhsnail.finance.service;


import cn.hutool.extra.servlet.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.ThreadLocalVariables;
import com.zhsnail.finance.entity.ActivitiDeployment;
import com.zhsnail.finance.entity.ActivitiModel;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.ActivitiDeploymentMapper;
import com.zhsnail.finance.mapper.ActivitiModelMapper;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.vo.DeployMentVo;
import com.zhsnail.finance.vo.ModelVo;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.*;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private FormService formService;
    @Autowired
    private ActivitiModelMapper activitiModelMapper;
    @Autowired
    private ActivitiDeploymentMapper activitiDeploymentMapper;

    @Override
    public PageInfo<ActivitiModel> findAllModel(ModelVo modelVo) {
        PageHelper.startPage(modelVo.getPageNum(), modelVo.getPageSize(), true);
        List<ActivitiModel> list = activitiModelMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<ActivitiDeployment> findAllDeployMent(DeployMentVo deployMentVo) {
        PageHelper.startPage(deployMentVo.getPageNum(), deployMentVo.getPageSize(), true);
        List<ActivitiDeployment> list = activitiDeploymentMapper.findAll();
        return new PageInfo<>(list);
    }


    @Override
    public ProcessInstance runStart(String workKey, String businessKey, Map variables) {
        ThreadLocalVariables.remove();
        if (variables == null) {
            variables = new HashMap();
        }
        //清空审批意见，防止退转的意见带到申请意见里
        variables.put(DICT.COMMENT, "");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(workKey).singleResult();
        if (processDefinition == null) {
            throw new BaseRuningTimeException("activiti.action.notFoundProcessDefinition", new String[]{workKey});
        }
        // 判断流程是否启动
        ProcessInstance processInstance = findProcessInstanceByBusinessKey(workKey, businessKey);
        //退回后重新提交的走的流程
        if (processInstance != null) {
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            // 申请操作
            variables.put(DICT.ACTION, DICT.APPLY);
            // 业务ID
            variables.put(DICT.BUSINESSKEY, businessKey);
            // 工作流ID
            variables.put(DICT.WORKKEY, workKey);
            //已经停留在申请了，所以直接完成该节点
//            taskService.complete(task.getId(), variables);
        } else {
            // 否则，启动流程
            // 设置流程常用变量
            // 申请操作
            variables.put(DICT.ACTION, DICT.APPLY);
            // 业务ID
            variables.put(DICT.BUSINESSKEY, businessKey);
            // 工作流ID
            variables.put(DICT.WORKKEY, workKey);
            // 绑定流程启动人
            identityService.setAuthenticatedUserId("123");
            processInstance = runtimeService.startProcessInstanceByKey(workKey, businessKey, variables);
            //自动跳过第一步审批
            Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
            taskService.complete(task.getId(), variables);
        }
        return processInstance;
    }

    @Override
    public void runApprove(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if (variables == null) {
            variables = new HashMap();
        }
        // 设置操作为同意
        variables.put(DICT.ACTION, DICT.APPROVE);
        // 业务ID
        variables.put(DICT.BUSINESSKEY, businessKey);
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            String processDefinitionId = task.getProcessDefinitionId();
            String excId = task.getExecutionId();
            //检查是否是最后一步审批
            boolean b = checkIsEnd(processDefinitionId, excId);
            if (b) {
                // 设置操作为最后一步审批
                variables.put(DICT.ACTION, DICT.LAST_APPROVE);
            }
            taskService.complete(task.getId(), variables);
        }
    }

    /**
     * 检查最后一次审批
     *
     * @param processDefinitionId 流程定义id
     * @param executionId         执行id
     * @return
     */
    private boolean checkIsEnd(String processDefinitionId, String executionId) {
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
        List<ActivityImpl> activitiList = def.getActivities();  //rs是指RepositoryService的实例
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionId).singleResult();
        String activitiId = execution.getActivityId();
        for (ActivityImpl activityImpl : activitiList) {
            String id = activityImpl.getId();
            if (activitiId.equals(id)) {
                System.out.println("当前任务：" + activityImpl.getProperty("name")); //输出某个节点的某种属性
                List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
                for (PvmTransition tr : outTransitions) {
                    PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                    String type = (String) ac.getProperty("type");
                    System.out.println("下一步任务任务：" + ac.getProperty("name"));
                    if ("endevent".equalsIgnoreCase(type)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void runBack(String workKey, String businessKey, Map variables) {

    }

    @Override
    public void runBackUpFlow(String workKey, String businessKey, Map variables) {

    }

    @Override
    public void runRevoke(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if(variables==null){
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION, DICT.REVOKE);
            Task task1 = taskService.newTask();
            taskService.complete(task1.getId(),variables);
            taskService.deleteTask(task.getId(),true);
            runtimeService.deleteProcessInstance(task.getProcessDefinitionId(),"");
            historyService.deleteHistoricProcessInstance(task.getProcessDefinitionId());
        }
    }

    @Override
    public void runDelete(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if(variables==null){
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION, DICT.DELETE);
            taskService.deleteTask(task.getId(),true);
            runtimeService.deleteProcessInstance(task.getProcessDefinitionId(),"");
            historyService.deleteHistoricProcessInstance(task.getProcessDefinitionId());
        }
    }

    @Override
    public void runRefuse(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if(variables==null){
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION, DICT.REFUSE);
            TaskEntity task1 = new TaskEntity(CodeUtil.getId());//(TaskEntity)taskService.newTask(DataUtil.generateID());
            ActivityImpl startActivityByKey = findStartActivityByKey(workKey);
            try {
                backProcess(task.getId(),startActivityByKey.getId(),variables);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BaseRuningTimeException(e);
            }
        }
    }


    @Override
    public ProcessInstance findProcessInstanceByBusinessKey(String workKey, String businessKey) {
        return runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey, workKey).singleResult();
    }

    @Override
    public List<HistoricProcessInstance> findHistoricProcessInstanceByBusinessKey(String workKey, String businessKey) {
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();

        if (StringUtils.isNotEmpty(workKey)) {
            query.processDefinitionKey(workKey);
        }
        query.processInstanceBusinessKey(businessKey);
        return query.list();
    }

    @Override
    public ProcessDefinition findProcessDefinitionById(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    }

    @Override
    public ActivityImpl findEndActivityByProcessDefineId(String processDefineId) {
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefineId);
        List<ActivityImpl> activityImpls = processDefinitionEntity.getActivities();
        ActivityImpl activityImplTemp = null;
        for (ActivityImpl activityImpl : activityImpls) {
            Object activityType = activityImpl.getActivityBehavior();
            if (activityType == null) {
                continue;
            } else if (activityType instanceof NoneEndEventActivityBehavior) {
                activityImplTemp = activityImpl;
                break;
            } else if (activityType instanceof CancelEndEventActivityBehavior) {
                activityImplTemp = activityImpl;
                break;
            } else if (activityType instanceof ErrorEndEventActivityBehavior) {
                activityImplTemp = activityImpl;
                break;
            } else if (activityType instanceof TerminateEndEventActivityBehavior) {
                activityImplTemp = activityImpl;
                break;
            }
        }
        if (activityImplTemp == null) {
            for (ActivityImpl activityImpl : activityImpls) {
                if (activityImpl.getOutgoingTransitions().size() == 0) {
                    activityImplTemp = activityImpl;
                    break;
                }
            }
        }
        return activityImplTemp;
    }

    /**
     * 查找第一个节点
     * @return
     */
    private ActivityImpl findStartActivityByKey(String workKey){
        ActivityImpl activityImplTemp = null ;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(workKey).latestVersion().singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinition.getId());
        List<ActivityImpl> activities = processDefinitionEntity.getActivities();
        for (ActivityImpl activityImpl : activities) {
            if(activityImpl.getActivityBehavior() instanceof NoneStartEventActivityBehavior){
                activityImplTemp = activityImpl;
                break;
            }
        }
        return activityImplTemp;
    }
    /**
     * 驳回流程
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            驳回节点ID
     * @param variables
     *            流程存储参数
     * @throws Exception
     */
    public void backProcess(String taskId, String activityId,
                            Map<String, Object> variables) throws Exception {
        if (StringUtils.isEmpty(activityId)) {
            throw new Exception("驳回目标节点ID为空！");
        }
        // 查询本节点发起的会签任务，并结束
        /*List<Task> tasks = taskService.createTaskQuery().parentTaskId(taskId)
                .taskDescription("jointProcess").list();
        for (Task task : tasks) {
            commitProcess(task.getId(), null, null);
        }*/
        ;
        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
        String taskDefinitionKey = taskService.createTaskQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).taskDefinitionKey(taskDefinitionKey).list();
        for (Task task : taskList) {
            turnTransition(taskId, activityId, variables);
        }
    }
    /**
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点
     *
     * @param taskId
     *            当前任务ID
     * @param currActivity
     *            当前活动节点
     * @param rtnList
     *            存储回退节点集合
     * @param tempList
     *            临时存储节点集合（存储一次迭代过程中的同级userTask节点）
     * @return 回退节点集合
     */
    private List<ActivityImpl> iteratorBackActivity(String taskId,
                                                    ActivityImpl currActivity, List<ActivityImpl> rtnList,
                                                    List<ActivityImpl> tempList) throws Exception {
        // 找到流程实例
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        // 当前节点的流入来源
        List<PvmTransition> incomingTransitions = currActivity
                .getIncomingTransitions();
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();
        // 遍历当前节点所有流入路径
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            ActivityImpl activityImpl = transitionImpl.getSource();
            String type = (String) activityImpl.getProperty("type");
            /**
             * 并行节点配置要求：<br>
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束)
             */
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归
                    return rtnList;
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                    parallelGateways.add(activityImpl);
                }
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归
                return rtnList;
            } else if ("userTask".equals(type)) {// 用户任务
                tempList.add(activityImpl);
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                currActivity = transitionImpl.getSource();
                exclusiveGateways.add(currActivity);
            }
        }

        /**
         * 迭代条件分支集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : exclusiveGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 迭代并行集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : parallelGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 根据同级userTask集合，过滤最近发生的节点
         */
        currActivity = filterNewestActivity(processInstance, tempList);
        if (currActivity != null) {
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID
            String id = findParallelGatewayId(currActivity);
            if (StringUtils.isEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点
                rtnList.add(currActivity);
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点
                currActivity = findActivitiImpl(taskId, id);
            }

            // 清空本次迭代临时集合
            tempList.clear();
            // 执行下次迭代
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);
        }
        return rtnList;
    }

    /**
     * 根据流入任务集合，查询最近一次的流入任务节点
     *
     * @param processInstance
     *            流程实例
     * @param tempList
     *            流入任务集合
     * @return
     */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,
                                              List<ActivityImpl> tempList) {
        while (tempList.size() > 0) {
            ActivityImpl activity_1 = tempList.get(0);
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(
                    processInstance, activity_1.getId());
            if (activityInstance_1 == null) {
                tempList.remove(activity_1);
                continue;
            }

            if (tempList.size() > 1) {
                ActivityImpl activity_2 = tempList.get(1);
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(
                        processInstance, activity_2.getId());
                if (activityInstance_2 == null) {
                    tempList.remove(activity_2);
                    continue;
                }

                if (activityInstance_1.getEndTime().before(
                        activityInstance_2.getEndTime())) {
                    tempList.remove(activity_1);
                } else {
                    tempList.remove(activity_2);
                }
            } else {
                break;
            }
        }
        if (tempList.size() > 0) {
            return tempList.get(0);
        }
        return null;
    }
    /**
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID
     *
     * @param activityImpl
     *            当前节点
     * @return
     */
    private String findParallelGatewayId(ActivityImpl activityImpl) {
        List<PvmTransition> incomingTransitions = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            activityImpl = transitionImpl.getDestination();
            String type = (String) activityImpl.getProperty("type");
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("END".equals(gatewayType.toUpperCase())) {
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))
                            + "_start";
                }
            }
        }
        return null;
    }

    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId 任务ID
     * @param activityId
     *            活动节点ID <br>
     *            如果为null或""，则默认查询当前活动节点 <br>
     *            如果为"end"，则查询结束节点 <br>
     *
     * @return
     * @throws Exception
     */
    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        String taskDefinitionKey = task.getTaskDefinitionKey();
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);

        // 获取当前活动节点ID
        if (StringUtils.isEmpty(activityId)) {
            activityId = taskDefinitionKey;
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }
    /**
     * 查询指定任务节点的最新记录
     *
     * @param processInstance
     *            流程实例
     * @param activityId
     * @return
     */
    private HistoricActivityInstance findHistoricUserTask(
            ProcessInstance processInstance, String activityId) {
        HistoricActivityInstance rtnVal = null;
        // 查询当前流程实例审批结束的历史节点
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().activityType("userTask")
                .processInstanceId(processInstance.getId()).activityId(
                        activityId).finished()
                .orderByHistoricActivityInstanceEndTime().desc().list();
        if (historicActivityInstances.size() > 0) {
            rtnVal = historicActivityInstances.get(0);
        }

        return rtnVal;
    }

    /**
     * 流程转向操作
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            目标节点任务ID
     * @param variables
     *            流程变量
     * @throws Exception
     */
    private void turnTransition(String taskId, String activityId,
                                Map<String, Object> variables) throws Exception {
        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);

        // 执行转向任务
        taskService.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }
    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @return 节点流向集合
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @param oriPvmTransitionList
     *            原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl,
                                   List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }
}