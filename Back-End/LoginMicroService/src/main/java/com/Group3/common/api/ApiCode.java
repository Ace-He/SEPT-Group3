package com.Group3.common.api;

public enum ApiCode {
    SUCCESS(200, "Operate successfully"),
    UNAUTHORIZED(401, "Access Violation"),
    NOT_PERMISSION(403, "Permission denied"),
    NOT_FOUND(404, "The resource you requested is not found!"),
    FAIL(500, "The operation failure"),
    LOGIN_EXCEPTION(4000, "Login failed"),
    SYSTEM_EXCEPTION(5000, "System exception"),
    PARAMETER_EXCEPTION(5001, "Request parameter verification is abnormal"),
    PARAMETER_PARSE_EXCEPTION(5002, "Request parameter resolution exception"),
    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP content type is abnormal"),
    YSHOP_SYSTEM_EXCEPTION(5100, "System Handling Exceptions"),
    BUSINESS_EXCEPTION(5101, "Service processing Exception"),
    DAO_EXCEPTION(5102, "Database processing Exception"),
    VERIFICATION_CODE_EXCEPTION(5103, "The verification code is abnormal procedure"),
    AUTHENTICATION_EXCEPTION(5104, "Login Authorization Exception"),
    UNAUTHENTICATED_EXCEPTION(5105, "No access permission"),
    UNAUTHORIZED_EXCEPTION(5106, "No access permission"),
    JWTDECODE_EXCEPTION(5107, "Token Resolution Exception"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(5108, "METHOD NOT SUPPORTED"),
    BAD_LIMIT_EXCEPTION(5109, "The access times are limited");

    private final int code;
    private final String message;

    private ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = values();
        ApiCode[] var2 = ecs;
        int var3 = ecs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ApiCode ec = var2[var4];
            if (ec.getCode() == code) {
                return ec;
            }
        }

        return SUCCESS;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
