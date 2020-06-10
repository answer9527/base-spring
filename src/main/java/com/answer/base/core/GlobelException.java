package com.answer.base.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobelException {
    @ExceptionHandler(value = Exception.class)
    public void handlerException(HttpServletRequest request,Exception e){
       System.out.println(11);
    }
}
