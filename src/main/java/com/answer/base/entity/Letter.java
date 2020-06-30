package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class Letter extends BaseEntity {
    private Integer id;
    private Integer uid;
    private String email;
    private String title;
    private String content;
    private Boolean pending;
    private Date post_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date planTime;
}
