package com.example.study.post;

public record PostCreateRequest(
        Long userId,
        String title,
        String content
) {
}
