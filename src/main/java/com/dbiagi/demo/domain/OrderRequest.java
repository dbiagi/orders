package com.dbiagi.demo.domain;

import com.dbiagi.demo.entity.Item;

import java.util.List;

public class OrderRequest {
    public String description;
    public List<ItemRequest> items;
}
