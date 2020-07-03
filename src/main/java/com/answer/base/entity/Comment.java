package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Comment extends BaseEntity {
    private Integer id;
    private Integer classicId;
    private Integer pid;
    private Integer uid;
    private Integer uid_r;
    private String userName;
    private String userAvatar;
    private String userName_r;
    private String userAvatar_r;
    private String content;
    private List<Comment> child;
}