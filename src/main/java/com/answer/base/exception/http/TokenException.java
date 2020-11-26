package com.answer.base.exception.http;

public class TokenException extends HttpException {
    public TokenException(int code) {
        this.code = code;
        this.httpStatusCode=403;
    }
}
