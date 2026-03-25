package com.example.study.post;

import java.time.OffsetDateTime;

public record PostDto(
        Long id,
        Long userId,
        String title,
        String content,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
