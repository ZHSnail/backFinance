package com.zhsnail.finance.exception;

import java.io.Serializable;

public class BaseRuningTimeException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 3988541965370355253L;

    private Throwable cause;
    private String[] args;

    public BaseRuningTimeException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public BaseRuningTimeException(String errorMssage, String... args) {
        super(errorMssage);
        this.args = args;
    }

    public BaseRuningTimeException(String errorMssage) {
        super(errorMssage);
    }

    public BaseRuningTimeException(String errorMssage, Throwable cause) {
        super(errorMssage, cause);
        this.cause = cause;
    }


    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
