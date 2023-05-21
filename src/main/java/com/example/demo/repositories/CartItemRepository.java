package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

}

