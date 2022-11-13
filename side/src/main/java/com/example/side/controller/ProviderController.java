package com.example.side.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.side.dto.ResponseDto;
import com.example.side.service.ProviderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/auth/provider/{id}")
    public ResponseDto<?> providerPage(@PathVariable Long id) {
        providerService.viewProviderPage();
        return new ResponseDto<>(true, "ok", null);
    }

    @PostMapping("/auth/provider")
    public ResponseDto<?> saveProvider() {
        return new ResponseDto<>(false, null, null);
    }

}
