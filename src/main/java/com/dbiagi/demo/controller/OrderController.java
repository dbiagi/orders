package com.dbiagi.demo.controller;

import com.dbiagi.demo.domain.OrderRequest;
import com.dbiagi.demo.domain.OrderResponse;
import com.dbiagi.demo.domain.OrderUpdateRequest;
import com.dbiagi.demo.service.OrderCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderCreateService orderCreateService;

    OrderController(OrderCreateService orderCreateService) {
        this.orderCreateService = orderCreateService;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        try {
            return orderCreateService.create(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrder(@RequestBody OrderUpdateRequest request) {

    }
}
