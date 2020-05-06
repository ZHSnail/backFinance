package com.zhsnail.finance.util;


import org.apache.shiro.SecurityUtils;

import java.util.Map;

public class CommonUtil {
    public static Map getCurrentUser(){
        return (Map) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
    }
}
