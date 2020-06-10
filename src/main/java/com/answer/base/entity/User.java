package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer age;
}
