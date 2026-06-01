package com.ex05.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String message) {
        this.message = message;
    }
}
