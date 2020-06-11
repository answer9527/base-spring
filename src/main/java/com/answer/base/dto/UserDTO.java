package com.answer.base.dto;

import com.answer.base.dto.validators.PasswordEqual;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Setter
@Getter
@PasswordEqual
public class UserDTO {
    private Integer id;
    private String userName;
    @Length(min = 5,max = 16,message = "password的长度为5-16")
    private String password;
    @Length(min = 5,max = 16,message = "password的长度为5-16")
    private String password2;
    @Min(value = 1,message = "age的长度必须大于0")
    private Integer age;
}
