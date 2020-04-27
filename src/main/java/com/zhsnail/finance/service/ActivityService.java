package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.ActivitiDeployment;
import com.zhsnail.finance.entity.ActivitiModel;
import com.zhsnail.finance.vo.DeployMentVo;
import com.zhsnail.finance.vo.ModelVo;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ActivityService {
    PageInfo<ActivitiModel> findAllModel(ModelVo modelVo);

    PageInfo<ActivitiDeployment> findAllDeployMent(DeployMentVo deployMentVo);


    /**
     * 启动
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     * @return 流程启动实例
     */
    ProcessInstance runStart(String workKey , String businessKey , Map variables);

    /**
     * 批准
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     */
    void runApprove(String workKey , String businessKey , Map variables);
    /**
     * 回退
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     *
     */
    void runBack(String workKey , String businessKey, Map variables);
    /**
     * 回退到上一步
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     *
     */
    void runBackUpFlow(String workKey , String businessKey, Map variables);
    /**
     * 撤销
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     */
    void runRevoke(String workKey , String businessKey ,Map variables);
    /**
     * 删除
     * @param workKey
     * @param businessKey
     * @param variables
     */
    void runDelete(String workKey , String businessKey ,Map variables);
    /**
     * 拒绝
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程参数
     */
    void runRefuse(String workKey , String businessKey,Map variables);

    /**
     * 查找processInstance对象
     * @param workKey 流程ID
     * @param businessKey 业务ID
     * @return processInstance对象
     */
    ProcessInstance findProcessInstanceByBusinessKey(String workKey,String businessKey);

    /**
     * 查找processDefinition对象
     * @param processDefinitionId 流程定义ID
     * @return ProcessDefinition
     */
    ProcessDefinition findProcessDefinitionById(String processDefinitionId);

    /**
     * 通过流程定义ID查找最后一个节点
     * @param processDefineId 流程定义ID
     * @return
     */
    ActivityImpl findEndActivityByProcessDefineId(String processDefineId);

    /**
     * 获取流程图片输入流
     * @param workKey 工作流key
     * @param businessKey 业务id
     * @return 图片输入流
     */
    InputStream resourceImage(String workKey,String businessKey);

    /**
     * 查找所有活动过的历史节点 启动时间正序排列
     * @param workKey 工作流key
     * @param businessKey 业务id
     * @return 历史节点list
     */
    List<HistoricActivityInstance> findHistoricActivityInstanceList(String workKey,String businessKey);

    /**
     * 查找审核信息
     * @param workKey 工作流key
     * @param businessKey 业务id
     * @return 页面所需的审核信息
     */
    List<Map<String, Object>> findApproveMsg(String workKey,String businessKey);
}
