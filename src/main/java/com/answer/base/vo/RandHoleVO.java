package com.answer.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RandHoleVO {
    private Integer id;
    private String title;
    private String content;
    private String userName;
    private Date createTime;
    private Integer gender;
}
