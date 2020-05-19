package com.zhsnail.finance.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.ThreadLocalVariables;
import com.zhsnail.finance.entity.ActivitiDeployment;
import com.zhsnail.finance.entity.ActivitiModel;
import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.ActivitiDeploymentMapper;
import com.zhsnail.finance.mapper.ActivitiModelMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.DeployMentVo;
import com.zhsnail.finance.vo.ModelVo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.*;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Override
    public PageInfo<ActivitiModel> findAllModel(ModelVo modelVo) {
        CommonUtil.startPage(modelVo);
        List<ActivitiModel> list = activitiModelMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<ActivitiDeployment> findAllDeployMent(DeployMentVo deployMentVo) {
        CommonUtil.startPage(deployMentVo);
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
            //TODO 需要放拒绝后原先的用户的id
            variables.put("applyUser", "userID");
            taskService.addComment(task.getId(),processInstance.getProcessInstanceId(),"重新提交","");
            //已经停留在申请了，所以直接完成该节点
            taskService.complete(task.getId(), variables);
        } else {
            // 否则，启动流程
            // 设置流程常用变量
            // 申请操作
            variables.put(DICT.ACTION, DICT.APPLY);
            // 业务ID
            variables.put(DICT.BUSINESSKEY, businessKey);
            // 工作流ID
            variables.put(DICT.WORKKEY, workKey);
            Map currentUser = CommonUtil.getCurrentUser();
            // 绑定流程启动人
            identityService.setAuthenticatedUserId((String) currentUser.get("id"));
            variables.put("applyUser",(String) currentUser.get("id"));
            processInstance = runtimeService.startProcessInstanceByKey(workKey, businessKey, variables);
            //自动跳过第一步审批
            Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
            taskService.addComment(task.getId(),processInstance.getProcessInstanceId(),"申请","");
            taskService.complete(task.getId(), variables);
        }
        log.info("开启workKey为{}工作流========================工作流的businessKey为：{}", workKey, businessKey);
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
            taskService.addComment(task.getId(),task.getProcessInstanceId(),"通过",variables.get(DICT.COMMENT) == null ? "": JsonUtil.obj2String(variables.get(DICT.COMMENT)));
            taskService.complete(task.getId(), variables);
        }
        log.info("审批通过workKey:{}的工作流businessKey:{}", workKey, businessKey);
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
        if (variables == null) {
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION,DICT.REVOKE);
            ActivityImpl startActivityByKey = findStartActivityByKey(workKey);
            try {
                taskService.addComment(task.getId(),task.getProcessInstanceId(),"撤回",variables.get(DICT.COMMENT) == null ? "": JsonUtil.obj2String(variables.get(DICT.COMMENT)));
                backProcess(task.getId(), startActivityByKey.getId(), variables);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BaseRuningTimeException(e);
            }
        }
        log.info("撤回workKey为:{}的工作流businessKey:{}", workKey, businessKey);
    }

    @Override
    public void runDelete(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if (variables == null) {
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION, DICT.DELETE);
            taskService.deleteTask(task.getId(), true);
            runtimeService.deleteProcessInstance(task.getProcessDefinitionId(), "");
            historyService.deleteHistoricProcessInstance(task.getProcessDefinitionId());
        }
    }

    @Override
    public void runRefuse(String workKey, String businessKey, Map variables) {
        // 先清理线程变量
        ThreadLocalVariables.remove();
        //分割流程参数与业务参数
        if (variables == null) {
            variables = new HashMap();
        }
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (task == null) {
            throw new BaseRuningTimeException("任务节点不存在");
        } else {
            variables.put(DICT.BUSINESSKEY, businessKey);
            variables.put(DICT.ACTION, DICT.REFUSE);
            ActivityImpl startActivityByKey = findStartActivityByKey(workKey);
            try {
                taskService.addComment(task.getId(),task.getProcessInstanceId(),"拒绝",variables.get(DICT.COMMENT) == null ? "": JsonUtil.obj2String(variables.get(DICT.COMMENT)));
                backProcess(task.getId(), startActivityByKey.getId(), variables);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BaseRuningTimeException(e);
            }
        }
        log.info("拒绝workKey:{}的工作流businessKey:{}", workKey, businessKey);
    }

    @Override
    public List<String> findCmtTask(String workkey, String roleId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(roleId).processDefinitionKey(workkey).orderByTaskCreateTime().desc().list();
        List<String> list = new ArrayList<>();
        for (Task task : tasks){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            list.add(processInstance.getBusinessKey());
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void deleteFlow(String workKey, String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(workKey).singleResult();
        runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(),"");
    }

    @Override
    public ProcessInstance findProcessInstanceByBusinessKey(String workKey, String businessKey) {
        return runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey, workKey).singleResult();
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

    @Override
    public List<Map<String, Object>> findApproveMsg(String workKey, String businessKey) {
        if (StringUtils.isEmpty(workKey) || StringUtils.isEmpty(businessKey)){
            throw new BaseRuningTimeException("workKey或businessKey不能为空");
        }
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(workKey).processInstanceBusinessKey(businessKey).singleResult();
        HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity) processInstance;
        //流程实例id
        String processInstanceId = historicProcessInstanceEntity.getProcessInstanceId();
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().desc().list();
        List<Map<String, Object>> maps = BeanUtil.objectsToMaps(historicActivityInstanceList);
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
        for (Map map : maps){
            List<Comment> comments = commentList.stream().filter(comment -> comment.getTaskId().equals(map.get("taskId"))).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(comments)){
                map.put("comment",comments.get(0));
            }
            //TODO 根据分配的用户插入审批角色
        }
        //过滤开始节点和结束节点
        return maps.stream().filter(map->!("startEvent".equals(map.get("activityType")) || "endEvent".equals(map.get("activityType")))).collect(Collectors.toList());
    }

    @Override
    public List<HistoricActivityInstance> findHistoricActivityInstanceList(String workKey, String businessKey) {
        if (StringUtils.isEmpty(workKey) || StringUtils.isEmpty(businessKey)){
            throw new BaseRuningTimeException("workKey或businessKey不能为空");
        }
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(workKey).processInstanceBusinessKey(businessKey).singleResult();
        HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity) processInstance;
        //流程实例id
        String processInstanceId = historicProcessInstanceEntity.getProcessInstanceId();
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc().list();
        return historicActivityInstances;
    }

    @Override
    public InputStream resourceImage(String workKey, String businessKey) {
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(workKey).processInstanceBusinessKey(businessKey).singleResult();
        HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity) processInstance;
        //流程实例id
        String processInstanceId = historicProcessInstanceEntity.getProcessInstanceId();
        //流程定义id
        String procDefId = processInstance.getProcessDefinitionId();
        //流程定义实体
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(procDefId);
        // 当前活动节点、活动线
        List<String> activeActivityIds = new ArrayList<>(), highLightedFlows;
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc().list();
        // 如果流程已经结束，则得到结束节点
        if (!isFinished(workKey, businessKey)) {
            // 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            Task task = taskService.createTaskQuery().processDefinitionKey(workKey).processInstanceBusinessKey(businessKey).singleResult();
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
            activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
            String activityId = execution.getActivityId();
            activeActivityIds.add(activityId);
            //拿到所有节点
            List<ActivityImpl> activities = processDefinitionEntity.getActivities();
            //前面的节点id
            List<String> tempActiveActivityIds = new ArrayList<>();
            getPreActivities(activityId,tempActiveActivityIds,activities);
            activeActivityIds.addAll(tempActiveActivityIds);
        }else {
            for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
                activeActivityIds.add(historicActivityInstance.getActivityId());
            }
        }
        // 计算活动线
        highLightedFlows = getHighLightedFlows(processDefinitionEntity,activeActivityIds);
        // 根据流程定义ID获得BpmnModel
        BpmnModel bpmnModel = repositoryService
                .getBpmnModel(procDefId);
        // 输出资源内容到相应对象
        InputStream imageStream = new DefaultProcessDiagramGenerator().generateDiagram(
                bpmnModel,
                "png",
                activeActivityIds,
                highLightedFlows,
                "宋体",
                "宋体",
                "宋体",
                processEngineConfiguration.getClassLoader(),
                1.0);
        return imageStream;
    }

    private boolean isFinished(String workKey, String businessKey) {
        return historyService.createHistoricProcessInstanceQuery().finished().processDefinitionKey(workKey).processInstanceBusinessKey(businessKey).count() > 0;
    }

    /**
     * 获取前面所有已激活的任务节点id
     * @param nowActivityId 当前已激活的任务id
     * @param activeActivityIds 激活的任务idlist
     * @param activities 流程定义的节点
     */
    private void getPreActivities(String nowActivityId,List<String> activeActivityIds,List<ActivityImpl> activities){
        List<ActivityImpl> activityList = activities.stream().filter(activity -> activity.getId().equals(nowActivityId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(activityList)){
            ActivityImpl activityImpl = activityList.get(0);
            //所有进来的线
            List<PvmTransition> incomingTransitions = activityImpl.getIncomingTransitions();
            for(PvmTransition incomingTransition:incomingTransitions){
                //进来的节点
                PvmActivity source = incomingTransition.getSource();
                activeActivityIds.add(source.getId());
                getPreActivities(source.getId(),activeActivityIds,activities);
            }
        }
    }

    /**
     * 获取当前激活的线
     * @param processDefinitionEntity 流程定义实体
     * @param activeActivityIds 已激活的节点id列表
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,List<String> activeActivityIds) {
        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId
        for(String activeActivityId:activeActivityIds){
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(activeActivityId);
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
            // 对所有的线进行遍历
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                if (activeActivityIds.contains(pvmActivityImpl.getId())) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return*/

  /*  private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId
        List<String> highActivitiImpl = new ArrayList<>();
        for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
            highActivitiImpl.add(historicActivityInstance.getActivityId());
        }
        for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstance.getActivityId());
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
            // 对所有的线进行遍历
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                if (highActivitiImpl.contains(pvmActivityImpl.getId())) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }*/


    /**
     * 查找第一个节点
     *
     * @return
     */
    private ActivityImpl findStartActivityByKey(String workKey) {
        ActivityImpl activityImplTemp = null;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(workKey).latestVersion().singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinition.getId());
        List<ActivityImpl> activities = processDefinitionEntity.getActivities();
        for (ActivityImpl activityImpl : activities) {
            if (activityImpl.getActivityBehavior() instanceof NoneStartEventActivityBehavior) {
                activityImplTemp = activityImpl;
                break;
            }
        }
        return activityImplTemp;
    }

    /**
     * 驳回流程
     *
     * @param taskId     当前任务ID
     * @param activityId 驳回节点ID
     * @param variables  流程存储参数
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
     * @param taskId       当前任务ID
     * @param currActivity 当前活动节点
     * @param rtnList      存储回退节点集合
     * @param tempList     临时存储节点集合（存储一次迭代过程中的同级userTask节点）
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
     * @param processInstance 流程实例
     * @param tempList        流入任务集合
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
     * @param activityImpl 当前节点
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
     * @param taskId     任务ID
     * @param activityId 活动节点ID <br>
     *                   如果为null或""，则默认查询当前活动节点 <br>
     *                   如果为"end"，则查询结束节点 <br>
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
     * @param processInstance 流程实例
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
     * @param taskId     当前任务ID
     * @param activityId 目标节点任务ID
     * @param variables  流程变量
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
     * @param activityImpl 活动节点
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
     * @param activityImpl         活动节点
     * @param oriPvmTransitionList 原有节点流向集合
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

    @Override
    public List<String> findCmtBizIds(String workkey) {
        Map currentUser = CommonUtil.getCurrentUser();
        List<Role> roles = (List<Role>) currentUser.get("roles");
        List<String> ids = new ArrayList<>();
        for (Role role : roles){
            List<String> bizIdList = findCmtTask(workkey,role.getId());
            ids.addAll(bizIdList);
        }
        ids = ids.stream().distinct().collect(Collectors.toList());
        return ids;
    }
}