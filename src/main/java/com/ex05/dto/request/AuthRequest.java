package com.ex05.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest() {
    }
}
