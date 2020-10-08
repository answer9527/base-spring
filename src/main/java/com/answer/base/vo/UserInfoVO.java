package com.answer.base.vo;

import com.answer.base.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoVO {
    private Integer id;
    private String userName;
    private String avatarUrl;
    private Integer gender;
    private String account;
}
