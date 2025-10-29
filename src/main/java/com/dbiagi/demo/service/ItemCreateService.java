package com.dbiagi.demo.service;

import com.dbiagi.demo.domain.ItemRequest;
import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Product;
import com.dbiagi.demo.repository.ItemRepository;
import com.dbiagi.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemCreateService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    ItemCreateService(ItemRepository itemRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Item create(ItemRequest itemRequest) {
        Optional<Product> product = this.productRepository.findById(itemRequest.product.id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        var item = new Item();
        item.setProduct(product.get());
        item.setNrUnits(itemRequest.nrUnits);
        item.setUnitPrice(product.get().getPrice());

        return itemRepository.save(item);
    }
}
