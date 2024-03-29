package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SetLetterImageDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String image;
    @NotNull
    private Integer uid;
}
