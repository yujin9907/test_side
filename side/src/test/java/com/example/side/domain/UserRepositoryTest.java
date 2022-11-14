package com.example.side.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import com.example.side.domain.user.User;
import com.example.side.domain.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Sql("classpath:dml.sql")
@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager tm;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 세이브테스트() {
        // given
        User user = User.builder()
                .username("TEST")
                .password("123")
                .role(Role.USER)
                .build();

        log.debug("디버그 : " + user.getUsername());

        // when
        User userR = userRepository.save(user);

        // then
        assertEquals(user.getUsername(), userR.getUsername());
    }

    @Test
    public void 조회테스트() {
        // given
        Long id = 1L;

        // when
        Optional<User> userPS = userRepository.findById(id);
        userPS.orElseThrow(() -> new RuntimeException());

        // then
        assertEquals(id, userPS.get().getId());

    }

    @Test
    public void 유저네임조회테스트() {
        // given
        String username = "ssar";

        // when
        User userPS = userRepository.findByUsername(username);

        // then
        assertEquals(1, userPS.getId());
    }

}
