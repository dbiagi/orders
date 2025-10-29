package com.dbiagi.demo.repository;

import com.dbiagi.demo.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
}
