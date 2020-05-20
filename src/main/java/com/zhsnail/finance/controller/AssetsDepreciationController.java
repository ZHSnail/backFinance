package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.AssetsDepreciationService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsDepreciationVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsDepreciationController {
    @Autowired
    private AssetsDepreciationService assetsDepreciationService;

    @PostMapping("/saveAssetsDepreciation")
    public Result saveOrUpdateAssetsDepreciation(@RequestBody AssetsDepreciationVo assetsDepreciationVo){
        assetsDepreciationService.saveOrUpdate(assetsDepreciationVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitAssetsDepreciation")
    public Result commitAssetsDepreciation(@RequestBody AssetsDepreciationVo assetsDepreciationVo){
        assetsDepreciationService.commitAssetsDepreciation(assetsDepreciationVo);
        return new Result(true,"保存成功");
    }

    @GetMapping("/assetsDepreciation/{id}")
    public Result findById(@PathVariable String id){
        AssetsDepreciationVo assetsDepreciationVo = assetsDepreciationService.findById(id);
        return new Result(assetsDepreciationVo);
    }

    @GetMapping("/assetsDepreciationList")
    public Result findAllAssetsDepreciation(@RequestParam String params) {
        AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
        if (StringUtils.isNotBlank(params)) {
            assetsDepreciationVo = JsonUtil.string2Obj(params, AssetsDepreciationVo.class);
        }
        PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo = assetsDepreciationService.findByCondition(assetsDepreciationVo);
        return new Result(assetsDepreciationVoPageInfo);
    }

    @GetMapping("/assetsDepreciationTaskList")
    public Result getAssetsDepreciationTaskList(){
        Map taskMapList = assetsDepreciationService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/allAssetsDepreciationTaskList")
    public Result findTaskListBycontion(@RequestParam String params){
        AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
        if (StringUtils.isNotBlank(params)) {
            assetsDepreciationVo = JsonUtil.string2Obj(params, AssetsDepreciationVo.class);
        }
        PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo = assetsDepreciationService.findTaskListByCondition(assetsDepreciationVo);
        return new Result(assetsDepreciationVoPageInfo);
    }

    @GetMapping("/assetsDepreciationCmtList")
    public Result findAssetsDepreciationCmtList(@RequestParam String params){
        AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
        if (StringUtils.isNotBlank(params)) {
            assetsDepreciationVo = JsonUtil.string2Obj(params, AssetsDepreciationVo.class);
        }
        PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo = assetsDepreciationService.findCmtTaskList(assetsDepreciationVo);
        return new Result(assetsDepreciationVoPageInfo);
    }
}
