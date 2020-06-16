package com.answer.base.dto;

import com.answer.base.core.enumeration.RegisterType;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.DigestUtils;

@Setter
@Getter
public class UserRegisterDTO {
    private Integer id;
    private String userName;
    @Length(min = 6 ,max = 32,message = "{token.password}")
    private String account;
    private String password;
    private String openid;
    private String avatarUrl;
    private Integer gender;

    public void setPassword(String password) {
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        this.password = md5Pass;
    }
}
