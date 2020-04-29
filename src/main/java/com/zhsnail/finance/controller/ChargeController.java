package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.service.DormInfoService;
import com.zhsnail.finance.service.ProfessionService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.DormInfoVo;
import com.zhsnail.finance.vo.ProfessionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    private DormInfoService dormInfoService;
    @Autowired
    private ProfessionService professionService;
    @GetMapping("/dormInfoList")
    public Result findAllDormInfo(@RequestParam String params){
        DormInfoVo dormInfoVo = new DormInfoVo();
        if(StringUtils.isNotBlank(params)){
            dormInfoVo = JsonUtil.string2Obj(params,DormInfoVo.class);
        }
        PageInfo<DormInfo> allDormInfo = dormInfoService.findAll(dormInfoVo);
        return new Result(allDormInfo);
    }
    @DeleteMapping("/dormInfo/{id}")
    public Result deleteDormInfo(@PathVariable String id){
        dormInfoService.deleteDormInfo(id);
        return new Result(true,"成功删除宿舍信息");
    }
    @PostMapping("/dormInfo")
    public Result saveDormInfo(@RequestBody DormInfoVo dormInfoVo){
        dormInfoService.saveDormInfo(dormInfoVo);
        return new Result(true,"添加宿舍信息成功");
    }

    @PutMapping("/dormInfo")
    public Result updateDormInfo(@RequestBody DormInfoVo dormInfoVo){
        dormInfoService.updateDormInfo(dormInfoVo);
        return new Result(true,"修改宿舍信息成功");
    }

    @GetMapping("/professionList")
    public Result findAllProfession(@RequestParam String params){
        ProfessionVo professionVo = new ProfessionVo();
        if(StringUtils.isNotBlank(params)){
            professionVo = JsonUtil.string2Obj(params,ProfessionVo.class);
        }
        PageInfo<Profession> professionPageInfo = professionService.findAll(professionVo);
        return new Result(professionPageInfo);
    }

    @PostMapping("/profession")
    public Result saveProfession(@RequestBody ProfessionVo professionVo){
        professionService.saveProfession(professionVo);
        return new Result(true,"添加专业信息成功");
    }

    @GetMapping("parentProfession")
    public Result findParentProfession(){
        return new Result(professionService.findParentProession());
    }

    @GetMapping("/parentProfession/{parentId}")
    public Result findByParentId(@PathVariable String parentId){
        return new Result(professionService.findByParentId(parentId));
    }

    @DeleteMapping("/profession/{id}")
    public Result deleteProfession(@PathVariable String id){
        professionService.deleteProfession(id);
        return new Result(true,"成功删除宿舍信息");
    }

    @PutMapping("/dormInfo")
    public Result updateProfession(@RequestBody ProfessionVo professionVo){
        professionService.updateProfession(professionVo);
        return new Result(true,"修改宿p舍信息成功");
    }
}
