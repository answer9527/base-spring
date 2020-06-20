package com.answer.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ChangeLikeCountDTO {
    private Integer cid;
    private Boolean isAdd;
}
