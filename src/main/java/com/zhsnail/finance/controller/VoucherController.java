package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
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

import java.util.Map;

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

    @GetMapping("/voucherTaskList")
    public Result getVoucherTaskList(){
        Map taskMapList = voucherService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/allTaskList")
    public Result findTaskListBycontion(@RequestParam String params){
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findTaskListByCondition(voucherVo);
        return new Result(voucherVoPageInfo);
    }

    @GetMapping("/voucherCmtList")
    public Result findVoucherCmtList(@RequestParam String params){
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findCmtTaskList(voucherVo);
        return new Result(voucherVoPageInfo);
    }

    @GetMapping("/cashierUntickVoucher")
    public Result findCashierUntickVoucher(@RequestParam String params) {
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findCashierList(voucherVo, DICT.VOUCHER_TICK_STATE_UNTICK);
        return new Result(voucherVoPageInfo);
    }

    @GetMapping("/cashierTickVoucher")
    public Result findCashierTickVoucher(@RequestParam String params) {
        VoucherVo voucherVo = new VoucherVo();
        if (StringUtils.isNotBlank(params)) {
            voucherVo = JsonUtil.string2Obj(params, VoucherVo.class);
        }
        PageInfo<VoucherVo> voucherVoPageInfo = voucherService.findCashierList(voucherVo, DICT.VOUCHER_TICK_STATE_TICKED);
        return new Result(voucherVoPageInfo);
    }

    @PutMapping("/tickVoucher/{id}")
    public Result tickVoucher(@PathVariable String id){
        voucherService.tickVoucher(id);
        return new Result(true,"勾对凭证成功");
    }

    @PostMapping("/postVoucher")
    public Result postVoucher(@RequestBody VoucherVo voucherVo){
        voucherService.postVoucher(voucherVo);
        return new Result(true,"过账凭证成功");
    }
}
