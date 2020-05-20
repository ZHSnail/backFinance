package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.StationInfo;
import com.zhsnail.finance.service.StationInfoService;
import com.zhsnail.finance.util.ExcelUtils;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.StationInfoVo;
import com.zhsnail.finance.vo.StationInfoVo;
import com.zhsnail.finance.vo.StationInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class StationInfoController {
    @Autowired
    private StationInfoService stationInfoService;
    @DeleteMapping("/stationInfo/{id}")
    public Result deleteById(@PathVariable String id){
        stationInfoService.deleteStationInfo(id);
        return new Result(true,"删除岗位信息成功");
    }

    @GetMapping("/stationInfoList")
    public Result findStationInfoByCondition(@RequestParam String params) {
        StationInfoVo stationInfoVo = new StationInfoVo();
        if (StringUtils.isNotBlank(params)) {
            stationInfoVo = JsonUtil.string2Obj(params, StationInfoVo.class);
        }
        PageInfo<StationInfoVo> all = stationInfoService.findAllByCondition(stationInfoVo);
        return new Result(all);
    }

    @GetMapping("/stationInfos")
    public Result findAllStationInfo() {
        return new Result(stationInfoService.findAll());
    }
    
    @PostMapping("/stationInfo")
    public Result saveStationInfo(@RequestBody StationInfoVo stationInfoVo) {
        stationInfoService.saveStationInfo(stationInfoVo);
        return new Result(true, "添加岗位信息成功");
    }

    @PutMapping("/stationInfo")
    public Result updateStationInfo(@RequestBody StationInfoVo stationInfoVo) {
        stationInfoService.updateStationInfo(stationInfoVo);
        return new Result(true, "修改岗位信息成功");
    }

    @PutMapping("/scaleAndStation")
    public Result updateScaleAndStation(@RequestBody StationInfoVo stationInfoVo) {
        stationInfoService.updateScaleAndStation(stationInfoVo);
        return new Result(true, "修改信息成功");
    }

    @GetMapping("/scaleAndStationList")
    public Result findScaleAndStationList() {
        return new Result(stationInfoService.findScaleAndStationList());
    }

    @GetMapping("/stationInfoExport")
    public void  exportStationInfo(HttpServletResponse response, @RequestParam String data){
        StationInfoVo stationInfoVo = new StationInfoVo();
        if (StringUtils.isNotBlank(data)) {
            stationInfoVo = JsonUtil.string2Obj(data, StationInfoVo.class);
        }
        List<StationInfoVo> stationInfoVos = stationInfoService.exportStationInfoVo(stationInfoVo);
        ExcelUtils.export2Web(response,"岗位信息表"+new Date().getTime(),"岗位信息申请表",StationInfoVo.class,stationInfoVos);
    }
}
