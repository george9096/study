package com.example.study.post;

public record PostUpdateRequest(
        Long id,
        String title,
        String content
) {
}
