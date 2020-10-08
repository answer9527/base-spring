package com.answer.base.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResultVO {
    private String token;
    private Integer uid;
    private UserInfoVO userInfo;
}
