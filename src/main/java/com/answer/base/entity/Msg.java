package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Msg extends BaseEntity {
    private Integer id;
    private Integer type;
    private Integer commentId;
    private Boolean isRead;
}
