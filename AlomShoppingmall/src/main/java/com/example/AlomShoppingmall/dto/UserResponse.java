package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private Integer age;
    private String email;
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
