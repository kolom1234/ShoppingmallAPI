package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    private Integer age;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    // Getters and setters

    public @NotNull @Size(max = 255) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Size(max = 255) @Email String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public @NotNull @Size(max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(max = 255) String password) {
        this.password = password;
    }

    public @NotNull @Size(max = 255) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(max = 255) String username) {
        this.username = username;
    }
}
