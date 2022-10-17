package com.Group3.common.exception;


import com.Group3.common.api.ApiCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -2470461654663264392L;
    private Integer errorCode;
    private String message;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setErrorCode(final Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String toString() {
        return "BusinessException(errorCode=" + this.getErrorCode() + ", message=" + this.getMessage() + ")";
    }
}
