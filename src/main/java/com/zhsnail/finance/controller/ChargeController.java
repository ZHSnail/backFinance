package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.service.*;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    private DormInfoService dormInfoService;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private FeeKindService feeKindService;
    @Autowired
    private PayNoticeService payNoticeService;

    @GetMapping("/dormInfoList")
    public Result findDormInfoByCondition(@RequestParam String params) {
        DormInfoVo dormInfoVo = new DormInfoVo();
        if (StringUtils.isNotBlank(params)) {
            dormInfoVo = JsonUtil.string2Obj(params, DormInfoVo.class);
        }
        PageInfo<DormInfo> allDormInfo = dormInfoService.findByCondition(dormInfoVo);
        return new Result(allDormInfo);
    }

    @GetMapping("/dormInfos")
    public Result findAllDormInfo() {
        List<DormInfo> dormInfoList = dormInfoService.findAll();
        return new Result(dormInfoList);
    }

    @DeleteMapping("/dormInfo/{id}")
    public Result deleteDormInfo(@PathVariable String id) {
        dormInfoService.deleteDormInfo(id);
        return new Result(true, "成功删除宿舍信息");
    }

    @PostMapping("/dormInfo")
    public Result saveDormInfo(@RequestBody DormInfoVo dormInfoVo) {
        dormInfoService.saveDormInfo(dormInfoVo);
        return new Result(true, "添加宿舍信息成功");
    }

    @PutMapping("/dormInfo")
    public Result updateDormInfo(@RequestBody DormInfoVo dormInfoVo) {
        dormInfoService.updateDormInfo(dormInfoVo);
        return new Result(true, "修改宿舍信息成功");
    }

    @GetMapping("/professionList")
    public Result findAllProfession(@RequestParam String params) {
        ProfessionVo professionVo = new ProfessionVo();
        if (StringUtils.isNotBlank(params)) {
            professionVo = JsonUtil.string2Obj(params, ProfessionVo.class);
        }
        PageInfo<Profession> professionPageInfo = professionService.findAll(professionVo);
        return new Result(professionPageInfo);
    }

    @PostMapping("/profession")
    public Result saveProfession(@RequestBody ProfessionVo professionVo) {
        professionService.saveProfession(professionVo);
        return new Result(true, "添加专业信息成功");
    }

    @GetMapping("parentProfession")
    public Result findParentProfession() {
        return new Result(professionService.findParentProession());
    }

    @GetMapping("/parentProfession/{parentId}")
    public Result findByParentId(@PathVariable String parentId) {
        return new Result(professionService.findByParentId(parentId));
    }

    @DeleteMapping("/profession/{id}")
    public Result deleteProfession(@PathVariable String id) {
        professionService.deleteProfession(id);
        return new Result(true, "成功删除专业信息");
    }

    @PutMapping("/profession")
    public Result updateProfession(@RequestBody ProfessionVo professionVo) {
        professionService.updateProfession(professionVo);
        return new Result(true, "修改专业信息成功");
    }

    @GetMapping("/easyProSelect")
    public Result getProfessionData() {
        List<Map> maps = professionService.generateProession(false);
        return new Result(maps);
    }

    @GetMapping("/proObjSelect")
    public Result getProfessionObj() {
        List<Map> maps = professionService.generateProession(true);
        return new Result(maps);
    }

    @PostMapping("/studentInfo")
    public Result saveStudentInfo(@RequestBody StudentInfoVo studentInfoVo){
        studentInfoService.saveStudentInfo(studentInfoVo);
        return new Result(true, "添加学生信息成功");
    }

    @DeleteMapping("/studentInfo/{id}")
    public Result deleteStudentInfo(@PathVariable String id){
        studentInfoService.deleteStudentInfo(id);
        return new Result(true, "删除学生信息成功");
    }

    @PutMapping("/studentInfo")
    public Result updateStudentInfo(@RequestBody StudentInfoVo studentInfoVo) {
        studentInfoService.updateStudentInfo(studentInfoVo);
        return new Result(true, "修改学生信息成功");
    }

    @GetMapping("/studentInfoList")
    public Result findAllStudentInfo(@RequestParam String params) {
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        if (StringUtils.isNotBlank(params)) {
            studentInfoVo = JsonUtil.string2Obj(params, StudentInfoVo.class);
        }
        PageInfo<StudentInfo> StudentPageInfo = studentInfoService.findByCondition(studentInfoVo);
        return new Result(StudentPageInfo);
    }

    @PostMapping("/feeKind")
    public Result saveFeeKind(@RequestBody FeeKindVo feeKindVo){
        feeKindService.saveFeeKind(feeKindVo);
        return new Result(true, "添加费用类别成功");
    }

    @DeleteMapping("/feeKind/{id}")
    public Result deleteFeeKind(@PathVariable String id){
        feeKindService.deleteFeeKind(id);
        return new Result(true, "删除费用类别成功");
    }

    @PutMapping("/feeKind")
    public Result updateFeeKind(@RequestBody FeeKindVo feeKindVo) {
        feeKindService.updateFeeKind(feeKindVo);
        return new Result(true, "修改费用类别成功");
    }

    @GetMapping("/feeKindList")
    public Result findFeeKindByCondition(@RequestParam String params) {
        FeeKindVo feeKindVo = new FeeKindVo();
        if (StringUtils.isNotBlank(params)) {
            feeKindVo = JsonUtil.string2Obj(params, FeeKindVo.class);
        }
        PageInfo<FeeKind> StudentPageInfo = feeKindService.findByCondition(feeKindVo);
        return new Result(StudentPageInfo);
    }

    @GetMapping("/allFeeKind")
    public Result  findAllFeeKind(){
        List<FeeKind> feeKindList = feeKindService.findAll();
        return new Result(feeKindList);
    }

    @PostMapping("/savePayNotice")
    public Result saveOrUpdatePayNotice(@RequestBody PayNoticeVo payNoticeVo){
        payNoticeService.saveOrUpdate(payNoticeVo);
        return new Result(true,"保存成功");
    }

    @PostMapping("/commitPayNotice")
    public Result commitPayNotice(@RequestBody PayNoticeVo payNoticeVo){
        payNoticeService.commit(payNoticeVo);
        return new Result(true,"提交成功");
    }

    @GetMapping("/PayNoticeTaskList")
    public Result getPayNoticeTaskList(){
        Map taskMapList = payNoticeService.findTaskMapList();
        return new Result(taskMapList);
    }

    @GetMapping("/payNotice/{id}")
    public Result findPayNotice(@PathVariable String id){
        return new Result(payNoticeService.findById(id));
    }

    @DeleteMapping("/payNotice/{id}")
    public Result deletePayNotice(@PathVariable String id){
        payNoticeService.deletePayNotice(id);
        return new Result(true,"删除成功");
    }

    @GetMapping("/payNoticeCmtList")
    public Result getPayNoticeCmtList(@RequestParam String params){
        PageEntity pageEntity = new PageEntity();
        if(StringUtils.isNotBlank(params)){
            pageEntity = JsonUtil.string2Obj(params,PageEntity.class);
        }
        return new Result(payNoticeService.findCmtTaskList(pageEntity));
    }
}
