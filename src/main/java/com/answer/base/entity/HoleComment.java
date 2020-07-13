package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class HoleComment {
    private Integer id;
    private Integer uid;
    private String userName;
    private String avatarUrl;
    private Integer uid_r;
    private String userName_r;
    private String avatarUrl_r;
    private Integer pid;
    private String content;
    private Date createTime;
    private List<HoleComment> child;
}
