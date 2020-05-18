package com.xc.exception;

public class BaseException extends Exception {
    protected String code;
    protected String message;

    public BaseException() {
    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
