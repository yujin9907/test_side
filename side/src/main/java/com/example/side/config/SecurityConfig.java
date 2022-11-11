package com.example.side.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@Configuration // 1. 빈 등록
@EnableWebSecurity // 2. 시큐리티 필터 등록
public class SecurityConfig {

    // 시큐리티 5.7부터는 컴포넌트 기반의 보안 설정 권장을 위해 deprecated 됨
    // wepsecurityconfigurerAdapter 대신 시큐리티 필터 체인 사용
    // 기존과 다른점 : 반환값이 있으며, 빈으로 등록해야 됨 => 컴보넌트 기반의 보안 설정 가능해짐

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // // 3. 시큐리티필터체인 빈 등록
    // http.csrf().disable(); // csrf 공격 방지 기능
    // http.authorizeRequests() // 인가 요청 설정
    // .antMatchers("/user/**").authenticated() // 인증된 사용자만 userpattern 요청 사용 가능
    // .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // access("권한") 권한에
    // 따른 접근 가능
    // .anyRequest().permitAll() // 위 설정 말고는 올허가 함
    // .and() // 요청 주소 관련, 로그인 폼 관련 나눠주기
    // // .formLogin() // 시큐리티 제공 로그인폼 이용
    // // .loginPage("/login") // 로그인 페이지 경로
    // // .defaultSuccessUrl("/")
    // // .and()
    // .logout().permitAll();
    // }
}
