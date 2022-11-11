package com.example.side.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.side.dto.ResponseDto;
import com.example.side.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/admin/{id}")
    public ResponseDto<?> adminPage() {

        return new ResponseDto<>(true, "성공", null);
    }

    @GetMapping("/auth/test/user/{id}")
    public ResponseDto<?> userPage() {

        return new ResponseDto<>(true, "성공", null);
    }

    @PostMapping("/auth")
    public ResponseDto<?> join() {

        return new ResponseDto<>(true, "성공", null);
    }

    @PutMapping("/auth/{id}")
    public ResponseDto<?> updateUser() {

        return new ResponseDto<>(true, "성공", null);
    }
}
