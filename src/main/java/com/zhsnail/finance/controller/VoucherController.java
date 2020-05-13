package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.service.VoucherService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.VoucherVo;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("/voucherList")
    public Result findAllVoucher(@RequestParam String params) {
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findByCondition(voucherVo);
        return new Result(voucherVoPageInfo);
    }

    @GetMapping("/voucher/{id}")
    public Result findById(@PathVariable String id){
        VoucherVo voucherVo = voucherService.findById(id);
        return new Result(voucherVo);
    }

    @GetMapping("/unpostVoucherList")
    public Result findUnpostVoucher(@RequestParam String params) {
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findUnpostVoucher(voucherVo);
        return new Result(voucherVoPageInfo);
    }

    @PostMapping("/saveVoucher")
    public Result saveOrUpdateVoucher(@RequestBody VoucherVo voucherVo){
        voucherService.saveOrUpdate(voucherVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitVoucher")
    public Result commitVoucher(@RequestBody VoucherVo voucherVo){
        voucherService.commitVoucher(voucherVo);
        return new Result(true,"保存成功");
    }
}
