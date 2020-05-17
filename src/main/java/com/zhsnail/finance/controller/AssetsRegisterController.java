package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.AssetsRegisterService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsRegisterController {
    @Autowired
    private AssetsRegisterService assetsRegisterService;

    @PostMapping("/saveAssetsRegister")
    public Result saveOrUpdateAssetsRegister(@RequestBody AssetsRegisterVo assetsRegisterVo){
        assetsRegisterService.saveOrUpdate(assetsRegisterVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitAssetsRegister")
    public Result commitAssetsRegister(@RequestBody AssetsRegisterVo assetsRegisterVo){
        assetsRegisterService.commitAssetsRegister(assetsRegisterVo);
        return new Result(true,"保存成功");
    }

    @GetMapping("/assetsRegister/{id}")
    public Result findById(@PathVariable String id){
        AssetsRegisterVo assetsRegisterVo = assetsRegisterService.findById(id);
        return new Result(assetsRegisterVo);
    }

    @GetMapping("/assetsRegisterList")
    public Result findAllAssetsRegister(@RequestParam String params) {
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        if (StringUtils.isNotBlank(params)) {
            assetsRegisterVo = JsonUtil.string2Obj(params, AssetsRegisterVo.class);
        }
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = assetsRegisterService.findByCondition(assetsRegisterVo);
        return new Result(assetsRegisterVoPageInfo);
    }

    @GetMapping("/assetsRegisterTaskList")
    public Result getAssetsRegisterTaskList(){
        Map taskMapList = assetsRegisterService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/allTaskList")
    public Result findTaskListBycontion(@RequestParam String params){
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        if (StringUtils.isNotBlank(params)) {
            assetsRegisterVo = JsonUtil.string2Obj(params, AssetsRegisterVo.class);
        }
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = assetsRegisterService.findTaskListByCondition(assetsRegisterVo);
        return new Result(assetsRegisterVoPageInfo);
    }

    @GetMapping("/assetsRegisterCmtList")
    public Result findAssetsRegisterCmtList(@RequestParam String params){
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        if (StringUtils.isNotBlank(params)) {
            assetsRegisterVo = JsonUtil.string2Obj(params, AssetsRegisterVo.class);
        }
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = assetsRegisterService.findCmtTaskList(assetsRegisterVo);
        return new Result(assetsRegisterVoPageInfo);
    }
}
