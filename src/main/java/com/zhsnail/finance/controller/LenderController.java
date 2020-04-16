package com.zhsnail.finance.controller;

import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.LenderService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lender")
public class LenderController {
    @Autowired
    private LenderService lenderService;

    @PostMapping("/account")
    public Result saveAccount(@RequestBody AccountVo accountVo){
        lenderService.saveAccount(accountVo);
        return new Result();
    }

    @DeleteMapping("/account/{id}")
    public Result deleteAccount(@PathVariable("id") String id){
        lenderService.deleteAccount(id);
        return new Result(true,"删除成功");
    }

    @GetMapping("/accountCondition")
    public Result findAllByCondition(@RequestParam String params){
        AccountVo accountVo = JsonUtil.string2Obj(params, AccountVo.class);
        List<Map> arrangeAccount = lenderService.findArrangeAccount(accountVo);
        return new Result(arrangeAccount);
    }
}
