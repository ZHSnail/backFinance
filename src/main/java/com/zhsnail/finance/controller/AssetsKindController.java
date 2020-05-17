package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.AssetsKind;
import com.zhsnail.finance.service.AssetsKindService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AssetsKindVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetsKindController {
    @Autowired
    private AssetsKindService assetsKindService;
    @DeleteMapping("/assetsKind/{id}")
    public Result deleteById(@PathVariable String id){
        assetsKindService.deleteById(id);
        return new Result(true,"删除资产类别信息成功");
    }

    @GetMapping("/assetsKindList")
    public Result findAssetsKindByCondition(@RequestParam String params) {
        AssetsKindVo assetsKindVo = new AssetsKindVo();
        if (StringUtils.isNotBlank(params)) {
            assetsKindVo = JsonUtil.string2Obj(params, AssetsKindVo.class);
        }
        PageInfo<AssetsKindVo> AssetsKindVoPageInfoList = assetsKindService.findByCondition(assetsKindVo);
        return new Result(AssetsKindVoPageInfoList);
    }

    @GetMapping("/assetsKinds")
    public Result findAllAssetsKind() {
        return new Result(assetsKindService.findAll());
    }
    
    @PostMapping("/assetsKind")
    public Result saveAssetsKind(@RequestBody AssetsKindVo assetsKindVo) {
        assetsKindService.saveAssetsKind(assetsKindVo);
        return new Result(true, "添加资产类别信息成功");
    }
}
