package com.dbiagi.demo.fixtures;

import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemFixtures {
    public static Item getItemWithProductId(UUID productId) {
        Item item = new Item();
        Product product = new Product();
        product.setId(productId);
        product.setDescription("product " + productId.toString());
        item.setProduct(product);
        item.setNrUnits(BigDecimal.ONE);

        return item;
    }
}
