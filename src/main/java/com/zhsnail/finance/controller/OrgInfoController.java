package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.OrgInfoService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.OrgInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
public class OrgInfoController {
    @Autowired
    private OrgInfoService orgInfoService;
    @DeleteMapping("/orgInfo/{id}")
    public Result deleteById(@PathVariable String id){
        orgInfoService.deleteOrgInfo(id);
        return new Result(true,"删除部门信息成功");
    }

    @GetMapping("/orgInfoList")
    public Result findOrgInfoByCondition(@RequestParam String params) {
        OrgInfoVo orgInfoVo = new OrgInfoVo();
        if (StringUtils.isNotBlank(params)) {
            orgInfoVo = JsonUtil.string2Obj(params, OrgInfoVo.class);
        }
        PageInfo<OrgInfoVo> all = orgInfoService.findAllByCondition(orgInfoVo);
        return new Result(all);
    }

    @GetMapping("/orgInfos")
    public Result findAllOrgInfo() {
        return new Result(orgInfoService.findAll());
    }

    @PostMapping("/orgInfo")
    public Result saveOrgInfo(@RequestBody OrgInfoVo orgInfoVo) {
        orgInfoService.saveOrgInfo(orgInfoVo);
        return new Result(true, "添加部门信息成功");
    }

    @PutMapping("/orgInfo")
    public Result updateOrgInfo(@RequestBody OrgInfoVo orgInfoVo) {
        orgInfoService.updateOrgInfo(orgInfoVo);
        return new Result(true, "修改部门信息成功");
    }
}
