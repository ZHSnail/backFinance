package com.zhsnail.finance.controller;

import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class LoginController {
    @Autowired
    private SystemService systemService;
    @PostMapping("/login")
    public Result checkUser(@RequestBody User user){
        Map map = new HashMap();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),user.getPsw());
            subject.login(usernamePasswordToken);
            if (null != subject.getSession())
            {
                String sessionId = (String)subject.getSession().getId();
                map.put("token",sessionId);
            }
        }catch (LockedAccountException e){
            Result result = new Result(false, e.getMessage());
            return result;
        }catch (DisabledAccountException e)
        {
            return new Result(false, e.getMessage());
        }
        catch (UnknownAccountException e)
        {
            return new Result(false, e.getMessage());
        }
        catch (IncorrectCredentialsException e)
        {
            /*SysUserEntity originUser = userService.queryByUsername(user.getUsername());
            userService.updateUserLoginAttempts(originUser,
                    configService.getValue("sysLoginErrorNum"));*/
            return new Result(false,"账号/密码不正确");
        }
        catch (Exception e)
        {
            return new Result(false,"账户验证失败");
        }
        return new Result(map);
    }

    @GetMapping("/loginOut")
    public Result loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result();
    }
}
