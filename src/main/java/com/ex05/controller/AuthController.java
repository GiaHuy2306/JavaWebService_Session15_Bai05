package com.ex05.controller;

import com.ex05.dto.request.AuthRequest;
import com.ex05.dto.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> users = new HashMap<>();

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {

        if (users.containsKey(authRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse("Username đã tồn tại"));
        }

        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        users.put(authRequest.getUsername(), encodedPassword);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse("Register successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        String storePassword = users.get(authRequest.getUsername());

        if (storePassword == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Username chưa chính xác"));
        }

        boolean match = passwordEncoder.matches(authRequest.getPassword(), storePassword);

        if (!match) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Mật khẩu chưa chính xác"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse("Login success"));
    }
}
