package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.AssetsPurchaseService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsPurchaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsPurchaseController {
    @Autowired
    private AssetsPurchaseService assetsPurchaseService;

    @PostMapping("/saveAssetsPurchase")
    public Result saveOrUpdateAssetsPurchase(@RequestBody AssetsPurchaseVo assetsPurchaseVo){
        assetsPurchaseService.saveOrUpdate(assetsPurchaseVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitAssetsPurchase")
    public Result commitAssetsPurchase(@RequestBody AssetsPurchaseVo assetsPurchaseVo){
        assetsPurchaseService.commitAssetsPurchase(assetsPurchaseVo);
        return new Result(true,"保存成功");
    }

    @GetMapping("/assetsPurchase/{id}")
    public Result findById(@PathVariable String id){
        AssetsPurchaseVo assetsPurchaseVo = assetsPurchaseService.findById(id);
        return new Result(assetsPurchaseVo);
    }

    @GetMapping("/assetsPurchaseList")
    public Result findAllAssetsPurchase(@RequestParam String params) {
        AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
        if (StringUtils.isNotBlank(params)) {
            assetsPurchaseVo = JsonUtil.string2Obj(params, AssetsPurchaseVo.class);
        }
        PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo = assetsPurchaseService.findByCondition(assetsPurchaseVo);
        return new Result(assetsPurchaseVoPageInfo);
    }

    @GetMapping("/assetsPurchaseTaskList")
    public Result getAssetsPurchaseTaskList(){
        Map taskMapList = assetsPurchaseService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/allAssetsPurchaseTaskList")
    public Result findTaskListBycontion(@RequestParam String params){
        AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
        if (StringUtils.isNotBlank(params)) {
            assetsPurchaseVo = JsonUtil.string2Obj(params, AssetsPurchaseVo.class);
        }
        PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo = assetsPurchaseService.findTaskListByCondition(assetsPurchaseVo);
        return new Result(assetsPurchaseVoPageInfo);
    }

    @GetMapping("/assetsPurchaseCmtList")
    public Result findAssetsPurchaseCmtList(@RequestParam String params){
        AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
        if (StringUtils.isNotBlank(params)) {
            assetsPurchaseVo = JsonUtil.string2Obj(params, AssetsPurchaseVo.class);
        }
        PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo = assetsPurchaseService.findCmtTaskList(assetsPurchaseVo);
        return new Result(assetsPurchaseVoPageInfo);
    }
}
