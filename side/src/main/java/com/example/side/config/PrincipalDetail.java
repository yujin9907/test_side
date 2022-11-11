package com.example.side.config;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.cache.spi.support.CollectionReadOnlyAccess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.side.domain.User;

import lombok.RequiredArgsConstructor;

// 세션의 유저 정보를 꺼내기 위해서
@RequiredArgsConstructor
public class PrincipalDetail implements UserDetails { // userdetials 타입이므로 authentication 객체에 넣을 수 있음.

    // private User user; // 컴포지션
    // public PrincipalDetail(User user) {
    // this.user=user;
    // }

    private final User user;

    // 해당 유저의 권한을 리턴하는 곳
    // 리턴 타입에 맞도록 변환 필요
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    // 비밀번호 리턴
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 잠금 계정 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정비밀번호 변경 기간 됐니
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 니 계정 활성화?
    @Override
    public boolean isEnabled() {
        return true;
    }

}
