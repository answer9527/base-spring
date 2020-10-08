package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    private Integer id;
    private String userName;
    private String avatarUrl;
    private Integer gender;
}
