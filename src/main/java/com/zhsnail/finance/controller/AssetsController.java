package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.AssetsKindService;
import com.zhsnail.finance.service.AssetsService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsVo;
import com.zhsnail.finance.vo.AssetsKindVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    @Autowired
    private AssetsService assetsService;

    @GetMapping("purchaseAssetsList")
    public Result findPurchaseAssetsList(){
        return new Result(assetsService.findPurchaseAssetsList());
    }

    @GetMapping("/assetsList")
    public Result findAllAssets(@RequestParam String params) {
        AssetsVo assetsVo = new AssetsVo();
        if (StringUtils.isNotBlank(params)) {
            assetsVo = JsonUtil.string2Obj(params, AssetsVo.class);
        }
        PageInfo<AssetsVo> assetsVoPageInfo = assetsService.findByCondition(assetsVo);
        return new Result(assetsVoPageInfo);
    }

    @GetMapping("/selectAssetsChangeList")
    public Result findAllSelectChangeAssets(@RequestParam String params) {
        AssetsVo assetsVo = new AssetsVo();
        if (StringUtils.isNotBlank(params)) {
            assetsVo = JsonUtil.string2Obj(params, AssetsVo.class);
        }
        PageInfo<AssetsVo> assetsVoPageInfo = assetsService.findAllChangeAssets(assetsVo);
        return new Result(assetsVoPageInfo);
    }
}
