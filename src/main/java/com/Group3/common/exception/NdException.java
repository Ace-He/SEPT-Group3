package com.Group3.common.exception;



import com.Group3.common.api.ApiCode;

import java.util.Objects;

public class NdException extends RuntimeException {
    private static final long serialVersionUID = -2470461654663264392L;
    private Integer errorCode;
    private String message;

    public NdException() {
    }

    public NdException(String message) {
        super(message);
        this.message = message;
    }

    public NdException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public NdException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public NdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NdException(Throwable cause) {
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
        return "CqutException(errorCode=" + this.getErrorCode() + ", message=" + this.getMessage() + ")";
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NdException;
    }


    @Override
    public int hashCode() {
        return Objects.hash(errorCode, message);
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof NdException)) {
            return false;
        } else {
            NdException other = (NdException) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$errorCode = this.getErrorCode();
                Object other$errorCode = other.getErrorCode();
                if (this$errorCode == null) {
                    if (other$errorCode != null) {
                        return false;
                    }
                } else if (!this$errorCode.equals(other$errorCode)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }




}
