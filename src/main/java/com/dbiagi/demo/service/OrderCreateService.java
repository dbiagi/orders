package com.dbiagi.demo.service;

import com.dbiagi.demo.domain.ItemRequest;
import com.dbiagi.demo.domain.OrderRequest;
import com.dbiagi.demo.domain.OrderResponse;
import com.dbiagi.demo.entity.Item;
import com.dbiagi.demo.entity.Order;
import com.dbiagi.demo.repository.ItemRepository;
import com.dbiagi.demo.repository.OrderRepository;
import com.dbiagi.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderCreateService {
    private final OrderRepository orderRepository;
    private final ItemCreateService itemCreateService;

    OrderCreateService(OrderRepository orderRepository, ItemCreateService itemCreateService) {
        this.orderRepository = orderRepository;
        this.itemCreateService = itemCreateService;
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        List<Item> items = request.items.stream().map(itemCreateService::create).toList();

        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            total = total.add(item.getUnitPrice().multiply(item.getNrUnits()));
        }

        Order order = new Order();
        order.setItems(items);
        order.setDescription(request.description);
        order.setDate(ZonedDateTime.now());
        order.setTotal(total);

        orderRepository.save(order);

        return new OrderResponse(order.getId());
    }
}
