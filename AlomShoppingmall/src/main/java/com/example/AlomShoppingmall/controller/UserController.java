package com.example.AlomShoppingmall.controller;

import com.example.AlomShoppingmall.dto.UserRequest;
import com.example.AlomShoppingmall.dto.UserResponse;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(user));
    }


    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) String username,
                                                @RequestParam(required = false) String email) {
        User user = userService.getUser(id, username, email);
        return ResponseEntity.ok(new UserResponse(user));
    }
}