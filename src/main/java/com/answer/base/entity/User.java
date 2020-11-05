package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends BaseEntity {
    private Integer id;
    private String userName;
    private String account;
    private String password;
    private Integer age;
    private String openId;
    private String avatarUrl;
    private Integer gender;
    private Integer scope;

    public String getAvatarUrl() {
        Boolean bool = avatarUrl.matches("http(.*)");
        if(bool){
            return avatarUrl;
        }else{
            return _domain()+avatarUrl;
        }
    }
}
