package com.example.side.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 1. 빈 등록
@EnableWebSecurity // 2. 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
// @EnableGlobalMethodSecurity(securedEnabled = true) // 시큐어 어노테이션 활성화, 각 컨트룰러
// 메서드에 시큐어드 어노테이션을 걸어서 접근 권한을 지정할 수 있음.\
// preAuthorize 도 알아보기
@EnableGlobalMethodSecurity(prePostEnabled = true)
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

    // ignore 설정 변경전
    // https://velog.io/@pjh612/Deprecated%EB%90%9C-WebSecurityConfigurerAdapter-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8C%80%EC%B2%98%ED%95%98%EC%A7%80

    // @Override
    // public void configure(WebSecurity web) {
    // web
    // .ignoring() // spring security 필터 타지 않도록 설정
    // .antMatchers("/resources/**") // 정적 리소스 무시
    // .antMatchers("/h2-console/**"); // h2-console 무시
    // }

    // 변경후

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/assets/**");
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                // url 별 권한설정
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/**/user/**").access("hasRole('USER')")
                .anyRequest().permitAll()
                // login 관련 설정
                .and()
                .formLogin() // 로그인 폼 사용
                // .loginPage("/경로") // 경로로 이동
                // .usernameParameter("username2") // 이렇게 바꿔줘야 동작함
                .loginProcessingUrl("/login") // "경로" 요청이 들어오면 시큐리티가 대신 처리함
                // .defaultSuccessUrl("/") // 로그인이 완료되면
                // .failureForwardUrl("/fail") // 실패하면 줄 거
                .and()
                .logout()
                .logoutUrl("/logout"); // 로그아웃 url 설정, 안하면 디폴트로 logout인가

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 비밀번호 암호화 할때 사용할 BCrypthPasswordEncoder 를 빈으로 등록
        return new BCryptPasswordEncoder();
    }

}
