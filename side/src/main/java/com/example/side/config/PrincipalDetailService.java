package com.example.side.config;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.cache.spi.support.CollectionReadOnlyAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.side.domain.user.User;
import com.example.side.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 1. di
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // /login 요청이 오면, 자동으로 userdetails service 타입으로 ioc되어있는 loadUserByUsername 실행
    // 규칙이므로 반드시 지켜야 됨.

    // 리턴되면 authentication 내부로 리턴값이 들어가고 session 내부로 그 authentication이 들어감
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // req 값을 반드시 username으로
                                                                                              // 받아야 됨. 여기 받는 파라미터 값이
                                                                                              // 유저네임이므로
        User userPS = userRepository.findByUsername(username);
        if (userPS != null) {
            return new PrincipalDetail(userPS);
        }
        return null;
    }

}
