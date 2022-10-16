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


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResult handler(IllegalArgumentException e) {
        log.error("Assert Exception——————————————————————>" + e.getMessage());
        return ApiResult.error().code(ApiCode.FAIL.getCode()).message(e.getMessage());
    }

    /**
     * Custom permission exception @AuthCheck
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnAuthenticatedException.class)
    public ApiResult<String> handleIllegalThreadStateExceptionHandler(UnAuthenticatedException e) {
        log.error("Custom permission exception @AuthCheck——————————————————————>" + e.getMessage());
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
     * Invalid parameter validation Exception
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
        String msg = "Can not be empty";
        if (!list.isEmpty()) {
            msg = list.get(0);
        }
        log.error(getApiCodeString(ApiCode.PARAMETER_EXCEPTION) + ":" + JSON.toJSONString(list));
        return ApiResult.error().code(ApiCode.PARAMETER_EXCEPTION.getCode()).message(msg);
    }


    /**
     * Custom business/data exception handling
     */
    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> ZqinException(BusinessException exception) {
        printRequestDetail();
        log.error("PolytechniCuniversity:", exception);
        return ApiResult.error().code(exception.getErrorCode()).message(exception.getMessage());
    }

    /**
     * Default exception handling
     *
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> httpRequestMethodNotSupportedExceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, exception);
        return ApiResult.error().code(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getCode()).message(exception.getMessage());
    }


    /**
     * Default exception handling
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> exceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.SYSTEM_EXCEPTION, exception);
        return ApiResult.ok().code(ApiCode.FAIL.getCode()).message(exception.getMessage());
    }


    /**
     * Print request details
     */
    private void printRequestDetail() {
        RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
        if (requestDetail != null) {
            log.error("Exception source：ip: {}, path: {}", requestDetail.getIp(), requestDetail.getPath());
        }
    }

    /**
     * Gets the ApiCode format string
     */
    private String getApiCodeString(ApiCode apiCode) {
        if (apiCode != null) {
            return String.format("errorCode: %s, errorMessage: %s", apiCode.getCode(), apiCode.getMessage());
        }
        return null;
    }

    /**
     * Print error codes and exceptions
     */
    private void printApiCodeException(ApiCode apiCode, Exception exception) {
        log.error(getApiCodeString(apiCode), exception);
    }

}
