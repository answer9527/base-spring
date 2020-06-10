package com.answer.base.core;

import com.answer.base.core.configuration.ExceptionCodeConfiguration;
import com.answer.base.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobelException {
    @Autowired
    private ExceptionCodeConfiguration exceptionCodeConfiguration;

//    处理统一的异常  未知异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public UnifyResponse handlerException(HttpServletRequest request,Exception e){
       String url = request.getRequestURI();
       String method = request.getMethod();
       return new UnifyResponse(9999,"服务异常",method+" " +url);
    }


//    处理已知的主动抛出的异常
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handlerHttpException(HttpServletRequest request, HttpException e){
        String url = request.getRequestURI();
        String method = request.getMethod();
        String msg = exceptionCodeConfiguration.getMessage(e.getCode());
        UnifyResponse unifyResponse = new UnifyResponse(e.getCode(),msg,method+" "+url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(unifyResponse,headers,httpStatus);
        return r;
    }

//
}


