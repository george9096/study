package com.example.study.auth;

import java.time.OffsetDateTime;

public record UserDto(
        Long id,
        String email,
        String passwordHash,
        String name,
        OffsetDateTime createdAt
) {
}
