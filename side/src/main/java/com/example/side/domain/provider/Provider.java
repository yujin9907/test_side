package com.example.side.domain.provider;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String providerName;
    private String password;
    private String phoneNumber;
    @CreationTimestamp
    private Timestamp createdAt;
    // @OneToMany(mappedBy = "provider")
    // private List<Merchandise> merchandiseList;
    // 일대다 양방향 매핑의 예시.
    // 의존관계는 단방향 매핑에서 끝난 거고, 양방향 매핑은 사실상 참조를 위해서 사용
    // 즉, 여기선 참조만 하고 연관관계의 주인을 merchandise의 provider 로 넘김 (mappedBy 설정)
}
