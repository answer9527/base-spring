package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hole extends BaseEntity {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Integer type;
}
