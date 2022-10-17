package com.Group3.common.exception;

import com.Group3.common.api.ApiCode;

public class UnAuthenticatedException extends NdException {
    public UnAuthenticatedException(String message) {
        super(message);
    }

    public UnAuthenticatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public UnAuthenticatedException(ApiCode apiCode) {
        super(apiCode);
    }
}