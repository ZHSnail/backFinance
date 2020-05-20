package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.SettleWageDetailService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.SettleWageDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
public class SettleWageDetailController {
    @Autowired
    private SettleWageDetailService settleWageDetailService;

    @GetMapping("/settleWageDetailList")
    public Result findSettleWageDetailByCondition(@RequestParam String params) {
        SettleWageDetailVo settleWageDetailVo = new SettleWageDetailVo();
        if (StringUtils.isNotBlank(params)) {
            settleWageDetailVo = JsonUtil.string2Obj(params, SettleWageDetailVo.class);
        }
        return new Result(settleWageDetailService.findAllByCondition(settleWageDetailVo));
    }

}
