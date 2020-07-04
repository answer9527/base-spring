package com.answer.base.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Suggest extends BaseEntity {
    private Integer id;
    private Integer uid;
    private String suggestInfo;
    private String replyInfo;
}
