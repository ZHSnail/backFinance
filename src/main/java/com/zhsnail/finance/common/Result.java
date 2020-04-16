package com.zhsnail.finance.common;

import java.io.Serializable;

public class Result implements Serializable {
    private String code=ResultCode.SUCCESS;
    private Object obj;
    private String msg;
    private boolean success=true;

    public Result(Object obj) {
        this.obj = obj;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.msg = message;
    }

    public Result(boolean success, String message, Object obj) {
        this.success = success;
        this.msg = message;
        this.obj = obj;
    }

    public Result() {

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
