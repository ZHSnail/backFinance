package com.zhsnail.finance.common;

import com.zhsnail.finance.util.JsonUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(ShiroFormAuthenticationFilter.class);

    public ShiroFormAuthenticationFilter() {
        super();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request,response)){
            if (isLoginSubmission(request, response)) {
                //执行登录
                return executeLogin(request, response);
            } else {
                return true;
            }
        }else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
            Result result = new Result(false, "未登录！！！");
            result.setCode(ResultCode.NOT_LOGIN);
            WebUtils.toHttp(response).getWriter().print(JsonUtil.obj2String(result));
            return false;
        }
    }
}
