package com.zhsnail.finance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.ActivitiDeployment;
import com.zhsnail.finance.entity.ActivitiModel;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.service.ActivityService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.ActivitiComment;
import com.zhsnail.finance.vo.DeployMentVo;
import com.zhsnail.finance.vo.ModelVo;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activiti")
public class ActivityController {
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
    private ActivityService activityService;
    /**
     * 创建模型
     */
 /*   @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "hello1111");
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = "hello1111";
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName("hello1111");
            modelData.setKey("12313123");

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            System.out.println("创建模型失败：");
        }
    }*/
    @PostMapping("/create")
    public Result createModel(@RequestBody ModelVo modelVo, HttpServletRequest request, HttpServletResponse response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode propertiesSetNode = objectMapper.createObjectNode();
            propertiesSetNode.put("process_id", modelVo.getKey());
            propertiesSetNode.put("name", modelVo.getName());
            editorNode.put("properties", propertiesSetNode);

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelVo.getName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelVo.getDesc());
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(modelVo.getName());
            modelData.setKey(modelVo.getKey());

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            Map<Object, Object> map = new HashMap<>();
            map.put("modelId",modelData.getId());
            return new Result(map);
            //            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            System.out.println("创建模型失败：");
            throw new BaseRuningTimeException(e);
        }
    }

    @GetMapping("/model")
    public Result findAllModel(@RequestParam String data){
        ModelVo modelVo = new ModelVo();
        if (StringUtils.isNotBlank(data)){
            modelVo = JsonUtil.string2Obj(data, ModelVo.class);
        }
        PageInfo<ActivitiModel> allModel = activityService.findAllModel(modelVo);
        return new Result(allModel);
    }

    @PostMapping("/deploy/{modelId}")
    public Result deployModel(@PathVariable String modelId){
        try {
            Model modelData = repositoryService.getModel(modelId);
            String deploymentId = modelData.getDeploymentId();
            if (StringUtils.isNotBlank(deploymentId)){
                Deployment deployment1 = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
                if (deployment1 != null){
                    repositoryService.deleteDeployment(deploymentId, true);
                }
            }
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "utf-8")).deploy();
            return new Result(true,"流程发布成功");
        }catch (Exception e){
            throw new BaseRuningTimeException(e);
        }
    }

    @PutMapping("/edit/{modelId}")
    public Result editModel(@PathVariable String modelId) throws UnsupportedEncodingException {
        Model modelData = repositoryService.getModel(modelId);
        Map<Object, Object> map = new HashMap<>();
        map.put("modelId",modelData.getId());
        return new Result(map);
    }

    @DeleteMapping("/delete/{modelId}")
    public Result deleteModel(@PathVariable String modelId){
        repositoryService.deleteModel(modelId);
        return new Result(true,"删除成功");
    }

    @GetMapping("/deployment")
    public Result findAllDeployment(@RequestParam String data){
        DeployMentVo deployMentVo = new DeployMentVo();
        if (StringUtils.isNotBlank(data)){
            deployMentVo = JsonUtil.string2Obj(data, DeployMentVo.class);
        }
        PageInfo<ActivitiDeployment> activitiDeploymentPageInfo = activityService.findAllDeployMent(deployMentVo);
        return new Result(activitiDeploymentPageInfo);
    }

    @PostMapping("/trantoModel/{processDefinitionId}")
    public Result changeProcessToModel(@PathVariable String processDefinitionId) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getDeploymentId());
        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);

        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
        return new Result(true,"转换成功");
    }

    @DeleteMapping("/deployment/{deploymentId}")
    public Result deleteDeployment(@PathVariable String deploymentId){
        repositoryService.deleteDeployment(deploymentId, true);
        return new Result(true,"删除成功");
    }

    @GetMapping("/image")
    public void viewActivityImage(HttpServletResponse response,@RequestParam String workKey, @RequestParam String businessKey) throws IOException {
        InputStream inputStream = activityService.resourceImage(workKey, businessKey);
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    @GetMapping("/record")
    public Result findActivitiRecord(@RequestParam String workKey, @RequestParam String businessKey){
        List<Map<String, Object>> approveMsg = activityService.findApproveMsg(workKey, businessKey);
        return new Result(approveMsg);
    }

    @PostMapping("/approve")
    public Result approve(@RequestBody ActivitiComment activitiComment){
        Map map = new HashMap<>();
        map.put(DICT.COMMENT,activitiComment.getComment());
        activityService.runApprove(activitiComment.getWorkKey(),activitiComment.getBusinessKey(),map);
        return new Result(true,"审核成功");
    }

    @PostMapping("/revoke")
    public Result revoke(@RequestBody ActivitiComment activitiComment){
        Map map = new HashMap<>();
        map.put(DICT.COMMENT,activitiComment.getComment());
        activityService.runRevoke(activitiComment.getWorkKey(),activitiComment.getBusinessKey(),map);
        return new Result(true,"撤回成功");
    }

    @PostMapping("/refuse")
    public Result refuse(@RequestBody ActivitiComment activitiComment){
        Map map = new HashMap<>();
        map.put(DICT.COMMENT,activitiComment.getComment());
        activityService.runRefuse(activitiComment.getWorkKey(),activitiComment.getBusinessKey(),map);
        return new Result(true,"已拒绝");
    }
}
