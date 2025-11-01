package com.dbiagi.demo.domain;

import com.dbiagi.demo.entity.Product;

import java.math.BigDecimal;

public record ItemRequest(ProductRequest product, BigDecimal nrUnits) {
}
