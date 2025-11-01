package com.dbiagi.demo.controller;

import com.dbiagi.demo.exception.OrderNotFoundException;
import com.dbiagi.demo.domain.error.ApiError;
import com.dbiagi.demo.domain.error.ApiErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletResponse response) throws IOException {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                List.of(new ApiError(ex.getMessage(), "ORDER_NOT_FOUND"))
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
}
