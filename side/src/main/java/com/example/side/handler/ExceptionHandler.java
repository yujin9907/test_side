package com.example.side.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.side.dto.ResponseDto;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseDto<?> commonException(Exception e) {
        return new ResponseDto<>(false, e.getMessage(), null);
    }
}