package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Classic extends BaseEntity {
    private Integer id;
    private Date recommend_time;
    private Boolean is_recommend=false;
    private String title;
    private String intro;
    private String sub_title;
    private String content;
    private Integer type;
    private String url;
    private String author;
    private String image;

}
