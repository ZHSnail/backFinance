package com.zhsnail.finance.common;

import com.zhsnail.finance.util.JsonUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 系统日志类
 */
@Document(collection = "systemLog")
public class SystemLog implements Serializable {
    private static final long serialVersionUID = -4000845735266995243L;
    private String id;//id
    private String userId;           //用户ID
    private String userName;         //用户名
    private String desc;            //日志描述
    private int isDeleted;           //状态标识
    private String menuName;         //菜单名称
    private String remoteAddr;       //请求地址
    private String requestUri;       //URI
    private String method;           //请求方式
    private String params;           //提交参数
    private String exception;        //异常信息
    private String type;             //日志类型

    @Override
    public String toString() {
        return "SystemLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", desc='" + desc + '\'' +
                ", isDeleted=" + isDeleted +
                ", menuName='" + menuName + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", exception='" + exception + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * 设置请求参数
     *
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        JsonUtil.obj2String(new Result());
        if (paramMap == null) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
//            params.append(org.apache.commons.lang3.StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.params = params.toString();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }


    public void setId(String id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
