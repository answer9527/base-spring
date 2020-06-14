package com.answer.base.dto;

import com.answer.base.core.enumeration.LoginType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WxTokenDTO {
    private String code;
    private String nickName;
    private String avatarUrl;
    private Integer gender;

}
