package com.example.side.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
