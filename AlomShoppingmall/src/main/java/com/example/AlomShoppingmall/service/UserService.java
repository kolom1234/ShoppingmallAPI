package com.example.AlomShoppingmall.service;

import com.example.AlomShoppingmall.dto.UserRequest;
import com.example.AlomShoppingmall.exception.DuplicateEmailException;
import com.example.AlomShoppingmall.exception.DuplicateUsernameException;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.repository.UserRepository;
import com.example.AlomShoppingmall.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder; // 암호화
    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new DuplicateEmailException("이미 사용 중인 이메일입니다.");
        }

        // 사용자 이름 중복 체크
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("이미 사용 중인 사용자 이름입니다.");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setNickname(userRequest.getNickname());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); // 비밀번호 암호화
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUser(Long id, String username, String email) {
        if (id != null) {
            return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당 id로 사용자를 찾을 수 없습니다."));
        } else if (username != null) {
            return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("해당 이름으로 사용자를 찾을 수 없습니다."));
        } else if (email != null) {
            return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("해당 이메일로 사용자를 찾을 수 없습니다."));
        } else {
            throw new IllegalArgumentException("하나 이상의 파라미터를 입력하세요.");
        }
    }
}
