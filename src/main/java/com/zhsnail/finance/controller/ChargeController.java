package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.service.DormInfoService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.DormInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    private DormInfoService dormInfoService;
    @GetMapping("/dormInfoList")
    public Result findAllDormInfo(@RequestParam String params){
        DormInfoVo dormInfoVo = new DormInfoVo();
        if(StringUtils.isNotBlank(params)){
            dormInfoVo = JsonUtil.string2Obj(params,DormInfoVo.class);
        }
        PageInfo<DormInfo> allDormInfo = dormInfoService.findAll(dormInfoVo);
        return new Result(allDormInfo);
    }
    @DeleteMapping("/dormInfo/{id}")
    public Result deleteDormInfo(@PathVariable String id){
        dormInfoService.deleteDormInfo(id);
        return new Result(true,"成功删除宿舍信息");
    }
    @PostMapping("/dormInfo")
    public Result saveDormInfo(@RequestBody DormInfoVo dormInfoVo){
        dormInfoService.saveDormInfo(dormInfoVo);
        return new Result(true,"添加宿舍信息成功");
    }

    @PutMapping("/dormInfo")
    public Result updateDormInfo(@RequestBody DormInfoVo dormInfoVo){
        dormInfoService.updateDormInfo(dormInfoVo);
        return new Result(true,"修改宿舍信息成功");
    }
}
