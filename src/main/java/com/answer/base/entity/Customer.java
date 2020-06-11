package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Customer extends BaseEntity {
    private Long id;
    private String avatarUrl;
    private Integer sex;
    private Date birth;
    private String job;
    private double height;
    private Integer maritalStatus;
    private double salary;
    private Integer houseNum;
    private Integer carNum;
//    籍贯
    private String nativePlace;
//    常住地
    private String resident;
    private String openId;
    private String name;
    private String password;
    private String account;
    private String tel;


}
