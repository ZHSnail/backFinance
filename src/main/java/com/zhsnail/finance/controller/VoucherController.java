package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.service.VoucherService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("/professionList")
    public Result findAllStudentInfo(@RequestParam String params) {
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<Voucher> voucherInfo = voucherService.findByCondition(voucherVo);
        return new Result(voucherInfo);
    }
}
