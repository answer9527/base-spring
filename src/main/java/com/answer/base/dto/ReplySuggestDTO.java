package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ReplySuggestDTO {
    @NotNull
    private Integer id;
    @Length(min = 2,message = "就这？你糊弄鬼吧。")
    private String replyInfo;
}
