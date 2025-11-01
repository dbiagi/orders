package com.dbiagi.demo.repository;

import com.dbiagi.demo.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends CrudRepository<Item, UUID> {
    @Override
    void delete(Item entity);
}
