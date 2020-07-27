package com.answer.base.exception.http;

import javax.xml.ws.http.HTTPException;

public class AuthException extends HttpException {

    public AuthException(int code) {
        this.httpStatusCode=404;
        this.code=code;
    }
}
