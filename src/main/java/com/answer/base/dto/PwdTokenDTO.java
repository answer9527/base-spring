package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.DigestUtils;

@Setter
@Getter
public class PwdTokenDTO {
    @Length(min = 6,max = 32,message = "{token.password}")
    private String account;
    private String password;

    public void setPassword(String password) {
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        this.password = md5Pass;
    }
}
