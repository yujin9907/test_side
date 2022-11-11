package com.example.side.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@Configuration // 1. 빈 등록
@EnableWebSecurity // 2. 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
// @EnableGlobalMethodSecurity(securedEnabled = true) // 시큐어 어노테이션 활성화, 각 컨트룰러
// 메서드에 시큐어드 어노테이션을 걸어서 접근 권한을 지정할 수 있음.\
// preAuthorize 도 알아보기
public class SecurityConfigClass {

    // 시큐리티 5.7부터는 컴포넌트 기반의 보안 설정 권장을 위해 deprecated 됨
    // wepsecurityconfigurerAdapter 대신 시큐리티 필터 체인 사용
    // 기존과 다른점 : 반환값이 있으며, 빈으로 등록해야 됨 => 컴보넌트 기반의 보안 설정 가능해짐
    // https://honeywater97.tistory.com/264

    // 시큐리티가 /login 요청을 처리함
    // 로그인 완료 -> 로그인 정보를 시큐리티 세션에 넣음. 같은 세션 공간인데, secruity ContextHolder라는 키값으로 별도로
    // 저장함
    // 시큐리티 컨텍스트 홀더는 authentication 타입의 객체만 받음
    // authentication에는 유저 정보가 필요한데,
    // 이 유저오브젝트 타입은 user details 타입이어야 됨.

    // 정리 security session => authentication => userDetails

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**/user/**").authenticated()
                .antMatchers("/auth/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and() // and로 구분
                .formLogin() // 로그인 폼 사용
                // .loginPage("/경로") // 경로로 이동
                // .usernameParameter("username2") // 이렇게 바꿔줘야 동작함
                .loginProcessingUrl("/login") // "경로" 요청이 들어오면 시큐리티가 대신 처리함
                .defaultSuccessUrl("/"); // 로그인이 완료되면

        return http.build();
    }

}
