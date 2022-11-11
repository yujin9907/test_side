package com.example.side.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.side.domain.User;
import com.example.side.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUser(Long id) {
        Optional<User> userPS = userRepository.findById(id);
        userPS.orElseThrow(() -> new RuntimeException());
        return userPS.get();
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        Optional<User> userPS = userRepository.findById(user.getId());
        userPS.orElseThrow(() -> new RuntimeException());

        userPS.get().setPassword(user.getPassword());
        userPS.get().setRole(user.getRole());

        return userPS.get();
    }
}