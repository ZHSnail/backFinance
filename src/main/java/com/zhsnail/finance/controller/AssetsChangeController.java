package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.AssetsChangeService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsChangeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsChangeController {
    @Autowired
    private AssetsChangeService assetsChangeService;

    @PostMapping("/saveAssetsChange")
    public Result saveOrUpdateAssetsChange(@RequestBody AssetsChangeVo assetsChangeVo){
        assetsChangeService.saveOrUpdate(assetsChangeVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitAssetsChange")
    public Result commitAssetsChange(@RequestBody AssetsChangeVo assetsChangeVo){
        assetsChangeService.commitAssetsChange(assetsChangeVo);
        return new Result(true,"保存成功");
    }

    @GetMapping("/assetsChange/{id}")
    public Result findById(@PathVariable String id){
        AssetsChangeVo assetsChangeVo = assetsChangeService.findById(id);
        return new Result(assetsChangeVo);
    }

    @GetMapping("/assetsChangeList")
    public Result findAllAssetsChange(@RequestParam String params) {
        AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
        if (StringUtils.isNotBlank(params)) {
            assetsChangeVo = JsonUtil.string2Obj(params, AssetsChangeVo.class);
        }
        PageInfo<AssetsChangeVo> assetsChangeVoPageInfo = assetsChangeService.findByCondition(assetsChangeVo);
        return new Result(assetsChangeVoPageInfo);
    }

    @GetMapping("/assetsChangeTaskList")
    public Result getAssetsChangeTaskList(){
        Map taskMapList = assetsChangeService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/allAssetsChangeTaskList")
    public Result findTaskListBycontion(@RequestParam String params){
        AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
        if (StringUtils.isNotBlank(params)) {
            assetsChangeVo = JsonUtil.string2Obj(params, AssetsChangeVo.class);
        }
        PageInfo<AssetsChangeVo> assetsChangeVoPageInfo = assetsChangeService.findTaskListByCondition(assetsChangeVo);
        return new Result(assetsChangeVoPageInfo);
    }

    @GetMapping("/assetsChangeCmtList")
    public Result findAssetsChangeCmtList(@RequestParam String params){
        AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
        if (StringUtils.isNotBlank(params)) {
            assetsChangeVo = JsonUtil.string2Obj(params, AssetsChangeVo.class);
        }
        PageInfo<AssetsChangeVo> assetsChangeVoPageInfo = assetsChangeService.findCmtTaskList(assetsChangeVo);
        return new Result(assetsChangeVoPageInfo);
    }
}
