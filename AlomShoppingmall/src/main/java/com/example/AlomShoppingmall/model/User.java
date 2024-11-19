package com.example.AlomShoppingmall.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String nickname;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    private Integer age;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    // 엔티티 간의 양방형 연관 관계 차후 고려 예정
    // 순환 참조 방지 기능 추가 가능할 시 수정
    // UserDetails 인터페이스 메서드 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 권한이 없으면 빈 리스트 반환
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email; // 이메일을 사용자명으로 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
