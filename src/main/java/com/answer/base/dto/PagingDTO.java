package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDTO {
    private Integer page=1;
    private Integer size=10;
    private Integer id;
    private String key;
}
