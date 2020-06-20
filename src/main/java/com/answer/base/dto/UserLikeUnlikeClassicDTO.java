package com.answer.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserLikeUnlikeClassicDTO {
    private Integer uid;
    private Integer cid;
}
