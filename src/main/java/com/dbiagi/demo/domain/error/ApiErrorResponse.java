package com.dbiagi.demo.domain.error;

import java.util.List;

public record ApiErrorResponse(
        List<ApiError> errors
) {
}
