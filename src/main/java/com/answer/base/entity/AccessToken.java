package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AccessToken extends BaseEntity {
    private Integer id;
    private String token;
}
