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
}
