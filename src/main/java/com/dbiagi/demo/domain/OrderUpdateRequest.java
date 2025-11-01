package com.dbiagi.demo.domain;

import java.util.List;
import java.util.UUID;

public record OrderUpdateRequest(
        UUID id,
        String description,
        List<ItemRequest> items
) {
}
