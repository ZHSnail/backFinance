package com.zhsnail.finance.common;

import com.zhsnail.finance.entity.Operation;
import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.service.SystemService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SystemService systemService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name= (String) principalCollection.getPrimaryPrincipal();
        User userInfo = systemService.findUserInfo(name);
        List<Role> roles = userInfo.getRoles();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色和权限
        for (Role role:roles){
            simpleAuthorizationInfo.addRole(role.getRoleName());
            /*for (Operation operation : role.getOperations()){
                simpleAuthorizationInfo.addStringPermission(operation.getModule()+"/"+operation.getOperationUrl());
            }*/
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
       /* if (authenticationToken.getPrincipal() == null) {
            return null;
        }*/
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = systemService.findUser(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPsw(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
