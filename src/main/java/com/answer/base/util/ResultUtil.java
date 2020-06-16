package com.answer.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultUtil {
    public static Msg success(Object object,String message){
        Msg msg = Msg.builder().message(message).code(0).data(object).build();
        return msg;
    }
    public static Msg success(Object object){
        Msg msg = Msg.builder().message("请求成功").code(0).data(object).build();
        return msg;
    }
    public static Msg success(String message){
        Msg msg = Msg.builder().message(message).code(0).build();
        return msg;
    }
    public static Msg success(){
        Msg msg = Msg.builder().message("请求成功").code(0).build();
        return msg;
    }
    public static Msg error(Integer code,String message){
        Msg msg = Msg.builder().message(message).code(code).build();
        return msg;
    }

}
