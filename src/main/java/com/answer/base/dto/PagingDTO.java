package com.answer.base.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDTO {
    private Integer page=1;
    private Integer size=10;
//    此处的key是用于分页查找的查找条件  可以是id  可以是type等等
    private Integer key;
    private String keyword;

}
