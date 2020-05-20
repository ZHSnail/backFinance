package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.PayStubInfoService;
import com.zhsnail.finance.util.ExcelUtils;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.PayStubInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class PayStubInfoController {
    @Autowired
    private PayStubInfoService payStubInfoService;
    @DeleteMapping("/payStubInfo/{id}")
    public Result deleteById(@PathVariable String id){
        payStubInfoService.deletePayStubInfo(id);
        return new Result(true,"删除工资单信息成功");
    }

    @GetMapping("/payStubInfoList")
    public Result findPayStubInfoByCondition(@RequestParam String params) {
        PayStubInfoVo payStubInfoVo = new PayStubInfoVo();
        if (StringUtils.isNotBlank(params)) {
            payStubInfoVo = JsonUtil.string2Obj(params, PayStubInfoVo.class);
        }
        PageInfo<PayStubInfoVo> all = payStubInfoService.findAllByCondition(payStubInfoVo);
        return new Result(all);
    }

    @GetMapping("/payStubInfos")
    public Result findAllPayStubInfo() {
        return new Result(payStubInfoService.findAll());
    }

    @PostMapping("/payStubInfo")
    public Result savePayStubInfo(@RequestBody PayStubInfoVo payStubInfoVo) {
        payStubInfoService.savePayStubInfo(payStubInfoVo);
        return new Result(true, "添加工资单信息成功");
    }

    @PutMapping("/payStubInfo")
    public Result updatePayStubInfo(@RequestBody PayStubInfoVo payStubInfoVo) {
        payStubInfoService.updatePayStubInfo(payStubInfoVo);
        return new Result(true, "修改工资单信息成功");
    }
}
