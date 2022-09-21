

package com.Group3.common.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
//@ToString
public class ApiResult<T> implements Serializable {

    @ApiModelProperty("响应码")
    private int status;
    @ApiModelProperty("是否成功：成功true，失败false")
    private boolean success;
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty("响应数据")
    private T data;
    @ApiModelProperty("响应时间")
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date time;

    public ApiResult() {
    }


    // 成功的静态方法
    public static ApiResult ok() {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(ApiCode.SUCCESS.getCode());
        r.setMsg(ApiCode.SUCCESS.getMessage());
        r.setTime(new Date());
        return r;
    }

    // 成功的静态方法
    public static <T> ApiResult ok(T data) {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(ApiCode.SUCCESS.getCode());
        r.setMsg(ApiCode.SUCCESS.getMessage());
        r.data(data);
        r.setTime(new Date());
        return r;
    }

    // 成功的静态方法
    public static ApiResult ok(ApiCode apiCode) {
        ApiResult r = new ApiResult();
        r.setSuccess(true);
        r.setStatus(apiCode.getCode());
        r.setMsg(apiCode.getMessage());
        r.setTime(new Date());
        return r;
    }

    // 失败的静态方法
    public static ApiResult error() {
        ApiResult r = new ApiResult();
        r.setSuccess(false);
        r.setStatus(ApiCode.FAIL.getCode());
        r.setMsg(ApiCode.FAIL.getMessage());
        r.setTime(new Date());
        return r;
    }

    // 失败的静态方法
    public static <T> ApiResult error(T data) {
        ApiResult r = new ApiResult();
        r.setSuccess(false);
        r.setStatus(ApiCode.FAIL.getCode());
        r.setMsg(ApiCode.FAIL.getMessage());
        r.data(data);
        r.setTime(new Date());
        return r;
    }

    // 失败的静态方法
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

    // return this 起到链式编程的效果
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
