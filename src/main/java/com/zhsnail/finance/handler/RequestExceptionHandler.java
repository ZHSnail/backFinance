package com.zhsnail.finance.handler;

import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.common.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class RequestExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result errorPage(){
        Result result = new Result(false, "接口不存在");
        result.setCode(ResultCode.NOT_FOUND);
        return result;
    }
}
