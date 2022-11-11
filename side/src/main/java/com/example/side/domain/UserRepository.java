package com.example.side.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 반환타입 메서드명 (매개변수)

    // findBy규칙 : 문법
    public User findByUsername(String username);
}
