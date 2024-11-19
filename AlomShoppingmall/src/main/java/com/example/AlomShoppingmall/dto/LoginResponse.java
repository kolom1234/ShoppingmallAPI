package com.example.AlomShoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String email;
    private String username;
    private String nickname;
    private String accessToken;

    public LoginResponse(String email, String username, String nickname, String accessToken) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.accessToken = accessToken;
    }

}

