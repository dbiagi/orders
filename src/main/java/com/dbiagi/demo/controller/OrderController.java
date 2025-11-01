package com.dbiagi.demo.controller;

import com.dbiagi.demo.domain.OrderRequest;
import com.dbiagi.demo.domain.OrderResponse;
import com.dbiagi.demo.domain.OrderUpdateRequest;
import com.dbiagi.demo.service.OrderCreateService;
import com.dbiagi.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderCreateService orderCreateService;
    private final OrderService orderService;

    OrderController(OrderCreateService orderCreateService, OrderService orderService) {
        this.orderCreateService = orderCreateService;
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        try {
            return orderCreateService.create(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@RequestBody OrderUpdateRequest request) {
        orderService.update(request);
    }
}
