package com.zhsnail.finance.controller;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.common.SystemControllerLog;
import com.zhsnail.finance.common.SystemLog;
import com.zhsnail.finance.service.TestServiceImpl;
import com.zhsnail.finance.util.MongoUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;

@RestController
public class aController {
    @Autowired
    private TestServiceImpl testService;
    @GetMapping("/test")
    @SystemControllerLog(description = "Xxx管理-执行Xxx操作")
    /*@RequiresRoles(value = {
        "xx",DICT.SYS_ROLE_NAME
    },logical = Logical.OR)*/
    public Result test(){
        List<SystemLog> all = MongoUtil.findAll();
        HashMap<Object, Object> map = new HashMap<>();
        testService.save();
        map.put("12","54567487");
        return new Result(all);
    }
}
