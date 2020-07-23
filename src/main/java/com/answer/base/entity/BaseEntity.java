package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class BaseEntity {
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

//    定义一个基类获取返回文件所在的域名的方法
    protected String _domain(){
        return "https://mina.huzhihua.cn";
    }
}
