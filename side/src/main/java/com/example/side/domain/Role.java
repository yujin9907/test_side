package com.example.side.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;
}
