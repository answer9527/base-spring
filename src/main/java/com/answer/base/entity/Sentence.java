package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sentence extends BaseEntity {
    private Integer id;
    private String txt;
    private String image;
}
