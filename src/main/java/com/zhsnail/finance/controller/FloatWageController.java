package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.FloatWageService;
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
public class FloatWageController {
    @Autowired
    private FloatWageService floatWageService;
    @DeleteMapping("/floatWage/{id}")
    public Result deleteById(@PathVariable String id){
        floatWageService.deleteFloatWage(id);
        return new Result(true,"删除浮动工资项信息成功");
    }

    @GetMapping("/floatWageList")
    public Result findFloatWageByCondition(@RequestParam String params) {
        FloatWageVo floatWageVo = new FloatWageVo();
        if (StringUtils.isNotBlank(params)) {
            floatWageVo = JsonUtil.string2Obj(params, FloatWageVo.class);
        }
        PageInfo<FloatWageVo> all = floatWageService.findAll(floatWageVo);
        return new Result(all);
    }

    @GetMapping("/floatWages")
    public Result findAllFloatWage() {
        return new Result(floatWageService.findAll(new FloatWageVo()));
    }

    @PostMapping("/floatWage")
    public Result saveFloatWage(@RequestBody FloatWageVo floatWageVo) {
        floatWageService.saveFloatWage(floatWageVo);
        return new Result(true, "添加浮动工资项信息成功");
    }

    @PutMapping("/floatWage")
    public Result updateFloatWage(@RequestBody FloatWageVo floatWageVo) {
        floatWageService.updateFloatWage(floatWageVo);
        return new Result(true, "修改浮动工资项信息成功");
    }

    @GetMapping("/floatWageExport")
    public void exportFloatWage(HttpServletResponse response, @RequestParam String data){
        FloatWageVo floatWageVo = new FloatWageVo();
        if (StringUtils.isNotBlank(data)) {
            floatWageVo = JsonUtil.string2Obj(data, FloatWageVo.class);
        }
        List<FloatWageVo> floatWageVos = floatWageService.exportFloatWageVo(floatWageVo);
        ExcelUtils.export2Web(response,"浮动工资项信息表"+new Date().getTime(),"浮动工资项信息表",FloatWageVo.class,floatWageVos);
    }
    @GetMapping("/selectFloatWageInfo")
    public Result getFloatWageInfoSelectData(){
        return new Result(floatWageService.generateFloatWageInfo());
    }
}
