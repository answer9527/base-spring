package com.answer.base.exception.http;

import lombok.Getter;
import lombok.Setter;


public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode=500;
    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
