package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Setter
@Getter
public class UserDTO {
    private String userName;
    private String password;
    @Min(1)
    private Integer age;
}
