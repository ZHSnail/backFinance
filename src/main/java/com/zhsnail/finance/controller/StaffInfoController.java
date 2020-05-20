package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.StaffInfoService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.StaffInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
public class StaffInfoController {
    @Autowired
    private StaffInfoService staffInfoService;
    @DeleteMapping("/staffInfo/{id}")
    public Result deleteById(@PathVariable String id){
        staffInfoService.deleteStaffInfo(id);
        return new Result(true,"删除员工信息成功");
    }

    @GetMapping("/staffInfoList")
    public Result findStaffInfoByCondition(@RequestParam String params) {
        StaffInfoVo staffInfoVo = new StaffInfoVo();
        if (StringUtils.isNotBlank(params)) {
            staffInfoVo = JsonUtil.string2Obj(params, StaffInfoVo.class);
        }
        PageInfo<StaffInfoVo> all = staffInfoService.findAllByCondition(staffInfoVo);
        return new Result(all);
    }

    @PostMapping("/staffInfoList")
    public Result findByCondition(@RequestBody StaffInfoVo staffInfoVo) {
        PageInfo<StaffInfoVo> all = staffInfoService.findAllByCondition(staffInfoVo);
        return new Result(all);
    }

    @GetMapping("/staffInfos")
    public Result findAllStaffInfo() {
        return new Result(staffInfoService.findAll());
    }

    @PostMapping("/staffInfo")
    public Result saveStaffInfo(@RequestBody StaffInfoVo staffInfoVo) {
        staffInfoService.saveStaffInfo(staffInfoVo);
        return new Result(true, "添加员工信息成功");
    }

    @PutMapping("/staffInfo")
    public Result updateStaffInfo(@RequestBody StaffInfoVo staffInfoVo) {
        staffInfoService.updateStaffInfo(staffInfoVo);
        return new Result(true, "修改员工信息成功");
    }

    @GetMapping("/staffInfo/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(staffInfoService.findById(id));
    }
}
