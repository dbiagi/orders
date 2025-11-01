package com.dbiagi.demo.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Order with id %s not found";
    private final UUID id;

    public OrderNotFoundException(UUID id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, id.toString());
    }
}
