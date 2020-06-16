package com.answer.base.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Msg<T> {
    private Integer code;
    private String message;
    private T data;
}
