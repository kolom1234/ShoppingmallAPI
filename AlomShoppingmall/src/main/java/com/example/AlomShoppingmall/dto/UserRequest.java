package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {
    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @Setter
    @Getter
    private Integer age;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    @NotNull
    @Size(max = 255)
    private String nickname;

    // Getters and setters

    public @NotNull @Size(max = 255) String getNickname() {
        return nickname;
    }

    public void setNickname(@NotNull @Size(max = 255) String nickname) {
        this.nickname = nickname;
    }

    public @NotNull @Size(max = 255) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Size(max = 255) @Email String email) {
        this.email = email;
    }

    public @NotNull @Size(max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(max = 255) String password) {
        this.password = password;
    }

    public @Size(max = 255) String getUsername() {
        return username;
    }

    public void setUsername( @Size(max = 255) String username) {
        this.username = username;
    }
}
