package com.dbiagi.demo.fixtures;

import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Order;
import com.dbiagi.demo.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderFixtures {
    public static Order orderWithItems() {
        // Create sample products
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("10.00"));

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("20.00"));

        // Create items for the order
        Item item1 = new Item();
        item1.setId(UUID.randomUUID());
        item1.setProduct(product1);
        item1.setNrUnits(new BigDecimal("2"));
        item1.setUnitPrice(product1.getPrice());

        Item item2 = new Item();
        item2.setId(UUID.randomUUID());
        item2.setProduct(product2);
        item2.setNrUnits(new BigDecimal("3"));
        item2.setUnitPrice(product2.getPrice());

        // Add items to a set
        List<Item> items = List.of(item1, item2);

        // Create the order and set items
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setDescription("Sample order with items");
        order.setItems(items);

        return order;
    }
}
