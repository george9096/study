package com.example.study.auth;

public record MeResponse(
        Long id,
        String email,
        String name
) {
}
