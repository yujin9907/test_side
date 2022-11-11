package com.example.side.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
    private boolean code;
    private String msg;
    private T data;
}
