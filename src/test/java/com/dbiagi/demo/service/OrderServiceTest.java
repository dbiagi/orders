package com.dbiagi.demo.service;

import com.dbiagi.demo.domain.ItemRequest;
import com.dbiagi.demo.domain.OrderUpdateRequest;
import com.dbiagi.demo.domain.ProductRequest;
import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Order;
import com.dbiagi.demo.fixtures.ItemFixtures;
import com.dbiagi.demo.fixtures.OrderFixtures;
import com.dbiagi.demo.repository.ItemRepository;
import com.dbiagi.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    private OrderRepository orderRepository = mock(OrderRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);
    private ItemCreateService itemCreateService = mock(ItemCreateService.class);
    private OrderService orderService = new OrderService(orderRepository, itemRepository, itemCreateService);

    @Test
    void givenAnOrderUpdateRequest_ShouldRemoveItemsNotInRequest() {
        // given
        var product1 = new ProductRequest(UUID.randomUUID());
        var product2 = new ProductRequest(UUID.randomUUID());

        var newItems = List.of(
                new ItemRequest(product1, BigDecimal.ONE),
                new ItemRequest(product2, BigDecimal.TEN)
        );
        var orderUpdateRequest = new OrderUpdateRequest(UUID.randomUUID(), "new description", newItems);
        var order = OrderFixtures.orderWithItems();

        // and
        when(orderRepository.findById(orderUpdateRequest.id())).thenReturn(Optional.of(order));
        when(itemCreateService.create(newItems.getFirst())).thenReturn(ItemFixtures.getItemWithProductId(product1.id()));
        when(itemCreateService.create(newItems.getLast())).thenReturn(ItemFixtures.getItemWithProductId(product2.id()));
        when(orderRepository.save(order)).thenReturn(order);

        // when
        Order result = orderService.update(orderUpdateRequest);

        // then
        verify(orderRepository).findById(orderUpdateRequest.id());
        verify(itemRepository, times(order.getItems().size())).delete(any());
        verify(itemCreateService, times(2)).create(any());

        List<Item> items = result.getItems();
        assertEquals(items.size(), newItems.size());
        assertTrue(items.stream().anyMatch(i -> i.getProduct().getId().equals(product1.id())));
        assertTrue(items.stream().anyMatch(i -> i.getProduct().getId().equals(product2.id())));
        assertEquals(result.getDescription(), orderUpdateRequest.description());
    }
}