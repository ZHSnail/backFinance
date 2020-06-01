package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.service.OrgInfoService;
import com.zhsnail.finance.service.UserInfoService;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.OrgInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/allUserInfo")
    public Result findAllUserInfo(){
        return new Result(userInfoService.findAllUserInfo());
    }
    @GetMapping("/getAllRoleList")
    public Result getAllRoleList(){
        return new Result(userInfoService.getAllRoleList());

    }
}
