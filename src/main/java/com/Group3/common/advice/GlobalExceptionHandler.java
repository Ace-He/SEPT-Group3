package com.Group3.common.advice;

import com.Group3.common.api.ApiCode;
import com.Group3.common.api.ApiResult;
import com.Group3.common.domain.RequestDetail;
import com.Group3.common.domain.RequestDetailThreadLocal;
import com.Group3.common.exception.BusinessException;
import com.Group3.common.exception.UnAuthenticatedException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全局异常
 *
 * @author zhonghui
 * @date 2020-04-30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = Exception.class)
//    public ApiResult handler(Exception e) {
//        log.error("全局异常——————————————————————>" + e.getMessage());
//        return ApiResult.error().code(ApiCode.FAIL.getCode()).message("Assert异常");
//    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResult handler(IllegalArgumentException e) {
        log.error("Assert异常——————————————————————>" + e.getMessage());
        return ApiResult.error().code(ApiCode.FAIL.getCode()).message(e.getMessage());
    }

    /**
     * 自定义权限异常 @AuthCheck
     *
     * @param e
     * @return
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnAuthenticatedException.class)
    public ApiResult<String> handleIllegalThreadStateExceptionHandler(UnAuthenticatedException e) {
        log.error("自定义权限异常 @AuthCheck——————————————————————>" + e.getMessage());
        e.printStackTrace();
        return ApiResult.error().code(ApiCode.UNAUTHENTICATED_EXCEPTION.getCode()).message(e.getMessage());
    }

    @ExceptionHandler(IllegalThreadStateException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleIllegalThreadStateExceptionHandler(IllegalThreadStateException e) {
        return ApiResult.error().code(ApiCode.VERIFICATION_CODE_EXCEPTION.getCode()).message(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handleRuntimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return ApiResult.error().code(ApiCode.FAIL.getCode()).message(ApiCode.FAIL.getMessage());
    }

    /**
     * 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ApiResult<String> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        printRequestDetail();
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        String msg = "不能为空";
        if (!list.isEmpty()) {
            msg = list.get(0);
        }
        log.error(getApiCodeString(ApiCode.PARAMETER_EXCEPTION) + ":" + JSON.toJSONString(list));
        return ApiResult.error().code(ApiCode.PARAMETER_EXCEPTION.getCode()).message(msg);
    }


    /**
     * 自定义业务/数据异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> ZqinException(BusinessException exception) {
        printRequestDetail();
        log.error("PolytechniCuniversity:", exception);
        return ApiResult.error().code(exception.getErrorCode()).message(exception.getMessage());
    }

    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> httpRequestMethodNotSupportedExceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, exception);
        return ApiResult.error().code(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getCode()).message(exception.getMessage());
    }

    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> exceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.SYSTEM_EXCEPTION, exception);
        // return ApiResult.fail(ApiCode.SYSTEM_EXCEPTION);
        return ApiResult.ok().code(ApiCode.FAIL.getCode()).message(exception.getMessage());
    }


    /**
     * 打印请求详情
     */
    private void printRequestDetail() {
        RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
        if (requestDetail != null) {
            log.error("异常来源：ip: {}, path: {}", requestDetail.getIp(), requestDetail.getPath());
        }
    }

    /**
     * 获取ApiCode格式化字符串
     *
     * @param apiCode
     * @return
     */
    private String getApiCodeString(ApiCode apiCode) {
        if (apiCode != null) {
            return String.format("errorCode: %s, errorMessage: %s", apiCode.getCode(), apiCode.getMessage());
        }
        return null;
    }

    /**
     * 打印错误码及异常
     *
     * @param apiCode
     * @param exception
     */
    private void printApiCodeException(ApiCode apiCode, Exception exception) {
        log.error(getApiCodeString(apiCode), exception);
    }

}
