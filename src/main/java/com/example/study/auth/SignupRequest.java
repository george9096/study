package com.example.study.auth;

public record SignupRequest(
        String email,
        String password,
        String name
) {
}
