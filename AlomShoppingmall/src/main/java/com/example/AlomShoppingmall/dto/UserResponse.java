package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.User;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String username;
    private Integer age;
    private String email;
    private LocalDateTime createdAt;

    // 생성자와 getter
    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
