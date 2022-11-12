package com.example.side.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.side.domain.User;
import com.example.side.dto.ResponseDto;
import com.example.side.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/admin/{id}")
    public ResponseDto<?> adminPage(@PathVariable Long id) {
        return new ResponseDto<>(true, "성공", userService.findByUser(id));
    }

    @GetMapping("/auth/test/user/{id}")
    public ResponseDto<?> userPage(@PathVariable Long id) {
        // user 테이블이 하나이므로 사실 id로 구분돼서 보여질 테지만
        // 테이블이 admin, user 따로 있다고 가정하고 생성
        return new ResponseDto<>(true, "성공", userService.findByUser(id));
    }

    @PostMapping("/auth")
    public ResponseDto<?> join(@RequestBody User user) {
        return new ResponseDto<>(true, "성공", userService.saveUser(user));
    }

    @PutMapping("/auth/{id}")
    public ResponseDto<?> updateUser() {
        return new ResponseDto<>(true, "성공", null);
    }
}
