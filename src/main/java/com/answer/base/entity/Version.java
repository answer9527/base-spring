package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Version extends BaseEntity {
    private Integer id;
    private String intro;
    private String release;
}
