package com.dbiagi.demo.service;

import com.dbiagi.demo.exception.OrderNotFoundException;
import com.dbiagi.demo.domain.ItemRequest;
import com.dbiagi.demo.domain.OrderUpdateRequest;
import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Order;
import com.dbiagi.demo.repository.ItemRepository;
import com.dbiagi.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemCreateService itemCreateService;

    OrderService(OrderRepository orderRepository, ItemRepository itemRepository, ItemCreateService itemCreateService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemCreateService = itemCreateService;
    }

    @Transactional
    public Order update(OrderUpdateRequest request) {
        var order = orderRepository.findById(request.id()).orElseThrow(
                () -> new OrderNotFoundException(request.id())
        );

        order.setDescription(request.description());
        var productIdsFromRequest = request.items()
                .stream()
                .map(i -> i.product().id())
                .toList();

        removeItems(productIdsFromRequest, order);
        updateOrCreateItems(request.items(), order);

        return orderRepository.save(order);
    }

    private void removeItems(List<UUID> productIdsFromRequest, Order order) {
        order.getItems().stream()
                .filter(item -> !productIdsFromRequest.contains(item.getProduct().getId()))
                .forEach(itemRepository::delete);

        List<Item> itemsToKeep = order.getItems().stream()
                .filter(item -> productIdsFromRequest.contains(item.getProduct().getId()))
                .toList();

        order.setItems(itemsToKeep);
    }

    private void updateOrCreateItems(List<ItemRequest> itemsFromRequest, Order order) {
        ArrayList<Item> updatedItems = new ArrayList<>(order.getItems());
        itemsFromRequest.forEach(itemRequest -> {
            var existingItem = order.getItems().stream()
                    .filter(i -> i.getProduct().getId().equals(itemRequest.product().id()))
                    .findFirst();

            if (existingItem.isPresent()) {
                existingItem.get().setNrUnits(itemRequest.nrUnits());
            } else {
                var newItem = itemCreateService.create(itemRequest);
                updatedItems.add(newItem);
            }
        });

        order.setItems(updatedItems);
    }
}
