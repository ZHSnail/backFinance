package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.FloatWageService;
import com.zhsnail.finance.service.SettleWageInfoService;
import com.zhsnail.finance.util.ExcelUtils;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.FloatWageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SettleWageInfoController {
    @Autowired
    private SettleWageInfoService settleWageInfoService;

    @GetMapping("settleWageInfo/{payStuId}")
    public Result findByPayStuId(@PathVariable String payStuId){
        return new Result(settleWageInfoService.findByPayStuId(payStuId));
    }

    @PostMapping("execPaySalary/{id}")
    public Result execPaySalary(@PathVariable String id){
        settleWageInfoService.execPaySalary(id);
        return new Result(true,"成功了");
    }
}
