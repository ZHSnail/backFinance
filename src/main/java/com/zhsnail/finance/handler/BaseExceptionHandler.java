package com.zhsnail.finance.handler;

import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.common.ResultCode;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BaseExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(HttpServletRequest req, Exception e){
        Result result = new Result(false, e.getMessage());
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        logger.error("",e);
        return result;
    }
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        Result result = new Result(false, e.getMessage());
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        logger.error("",e);
        return result;
    }
    @ExceptionHandler(UnauthorizedException.class)
    public Result UnauthorizedExceptionHandler(HttpServletRequest req, Exception e){
        Result result = new Result(false, "权限不足!!");
        result.setCode(ResultCode.UNAUTHORIZED);
        logger.error("",e);
        return result;
    }
}
