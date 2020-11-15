package com.answer.base.exception.http;

public class MailException extends HttpException {
    public MailException(int code) {
        this.httpStatusCode=404;
        this.code=code;
    }
}
