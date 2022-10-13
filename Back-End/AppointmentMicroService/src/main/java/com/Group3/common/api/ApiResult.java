

package com.Group3.common.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
//@ToString
public class ApiResult<T> implements Serializable {

    @ApiModelProperty("Response Code")
    private int status;
    @ApiModelProperty("True for success, False for failure")
    private boolean success;
    @ApiModelProperty("Response Message")
    private String msg;
    @ApiModelProperty("Response data")
    private T data;
    @ApiModelProperty("Response time")
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+10"
    )
    private Date time;

    public ApiResult() {
    }


    // Static methods for success
    public static ApiResult ok() {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(ApiCode.SUCCESS.getCode());
        r.setMsg(ApiCode.SUCCESS.getMessage());
        r.setTime(new Date());
        return r;
    }

    // Static methods for success
    public static <T> ApiResult ok(T data) {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(ApiCode.SUCCESS.getCode());
        r.setMsg(ApiCode.SUCCESS.getMessage());
        r.data(data);
        r.setTime(new Date());
        return r;
    }

    // Static methods for success
    public static ApiResult ok(ApiCode apiCode) {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(apiCode.getCode());
        r.setMsg(apiCode.getMessage());
        r.setTime(new Date());
        return r;
    }

    // Static methods for failure
    public static ApiResult error() {
        ApiResult r = new ApiResult();
        r.setSuccess(false);
        r.setStatus(ApiCode.FAIL.getCode());
        r.setMsg(ApiCode.FAIL.getMessage());
        r.setTime(new Date());
        return r;
    }

    // Static methods for failure
    public static <T> ApiResult error(T data) {
        ApiResult r = new ApiResult();
        r.setSuccess(false);
        r.setStatus(ApiCode.FAIL.getCode());
        r.setMsg(ApiCode.FAIL.getMessage());
        r.data(data);
        r.setTime(new Date());
        return r;
    }

    // Static methods for failure
    public static ApiResult error(ApiCode apiCode) {
        ApiResult r = new ApiResult();
        r.setSuccess(false);
        r.setStatus(apiCode.getCode());
        r.setMsg(apiCode.getMessage());
        r.setTime(new Date());
        return r;
    }

    public ApiResult<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    // Return this: Play the effect of chain programming
    public ApiResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ApiResult message(String message) {
        this.setMsg(message);
        return this;
    }

    public ApiResult<T> code(Integer code) {
        this.setStatus(code);
        return this;
    }

    public ApiResult data(T data) {
        this.data = data;
        return this;
    }


}
