package com.dbiagi.demo.domain.error;

public record ApiError(
        String message,
        String code
) {
}
