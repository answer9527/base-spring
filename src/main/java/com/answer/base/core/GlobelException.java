package com.answer.base.core;

import com.answer.base.core.configuration.ExceptionCodeConfiguration;
import com.answer.base.exception.http.HttpException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

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

//    处理Get请求校验异常
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handlerConstraintViolation(HttpServletRequest request,ConstraintViolationException e){
        String url = request.getRequestURI();
        String method = request.getMethod();
        String msg = e.getMessage();
        UnifyResponse unifyResponse = new UnifyResponse(9999,msg,method+" "+url);
        return unifyResponse;
    }
//    处理Get请求未传参数的异常
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handlerMissingParameter(HttpServletRequest request,MissingServletRequestParameterException e){
        String url = request.getRequestURI();
        String method = request.getMethod();
        String msg = e.getMessage();
        UnifyResponse unifyResponse = new UnifyResponse(9999,msg,method+" "+url);
        return unifyResponse;
    }

//    处理Post请求参数的异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handlerBeanValidation(HttpServletRequest request,MethodArgumentNotValidException e){
        String url = request.getRequestURI();
        String method = request.getMethod();
        String msg = e.getMessage();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        UnifyResponse unifyResponse = new UnifyResponse(9999,formatErrorMessage(errors),method+" "+url);
        return unifyResponse;
    }

    public String formatErrorMessage(List<ObjectError> errors){
        StringBuffer errMsg = new StringBuffer();
        errors.forEach(
                error->errMsg.append(error.getDefaultMessage()).append(",")
        );
        return errMsg.toString();
    }

//    捕捉token过期的异常
    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public UnifyResponse handlerTokenExpiredException(HttpServletRequest request, TokenExpiredException e){
        String url = request.getRequestURI();
        String method = request.getMethod();
//        String msg = e.getMessage();
        UnifyResponse unifyResponse = new UnifyResponse(40006,"token已过期",method+" "+url);
        return unifyResponse;
    }


}


