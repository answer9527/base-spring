package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Comment extends BaseEntity {
    private Long id;
    private Long classicId;
    private Long pid;
    private Long uid;
    private String userName;
    private String userAvatar;
    private String content;
    private List<Comment> child;
}
