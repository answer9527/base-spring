package com.answer.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SingleHoleVO {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private String userName;
    private Integer gender;
    private String avatarUrl;
    private Integer type;
    private Integer canComment;
    private Integer commentVisible;
    private Date createTime;
}
