package com.zhsnail.finance.common;

import java.security.PrivateKey;

public interface ResultCode {
    String SUCCESS = "200";//成功
    String FAIL= "400";//失败
    String UNAUTHORIZED = "401";//未认证（签名错误）
    String NOT_FOUND = "404";//接口不存在
    String INTERNAL_SERVER_ERROR = "500";//服务器内部错误
    String NOT_LOGIN = "501";
}
