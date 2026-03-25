package com.example.study.auth;

public record LoginRequest(
        String email,
        String password
) {
}
