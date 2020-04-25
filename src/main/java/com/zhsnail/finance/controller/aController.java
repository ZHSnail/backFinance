package com.zhsnail.finance.controller;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.common.SystemControllerLog;
import com.zhsnail.finance.common.SystemLog;
import com.zhsnail.finance.service.ActivityService;
import com.zhsnail.finance.service.TestServiceImpl;
import com.zhsnail.finance.util.MongoUtil;
import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class aController {
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
    private TestServiceImpl testService;
    @Autowired
    private ActivityService activityService;
    @GetMapping("/test")
    /*@RequiresRoles(value = {
        "xx",DICT.SYS_ROLE_NAME
    },logical = Logical.OR)*/
    public Result test(){
        Map map = new HashMap<>();
        map.put("applyUser","测试");
        map.put("testAudi","审核测试");
//        runtimeService.startProcessInstanceByKey("duogerenwu","98798798754165464",map);
        /*TaskQuery taskQuery = taskService.createTaskQuery().processInstanceBusinessKey("98798798754165464");
        Task task = taskQuery.singleResult();
        String formKey = task.getFormKey();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取businessKey 即业务id
        String businessKey = processInstance.getBusinessKey();
        //获取流程开始时的变量
        Map<String, VariableInstance> variableInstances = runtimeService.getVariableInstances(task.getExecutionId());
        taskService.complete(task.getId());*/
        Object principal = SecurityUtils.getSubject().getPrincipal();
//        activityService.runStart("duogerenwu","4564154165416541",map);
        activityService.runStart("duogerenwu","54987987987987987987987",null);
//        activityService.runRefuse("duogerenwu","4564154165416541",null);
        TaskQuery taskQuery = taskService.createTaskQuery().processInstanceBusinessKey("4564154165416541");
        Task task = taskQuery.singleResult();
        return new Result();
    }
}
