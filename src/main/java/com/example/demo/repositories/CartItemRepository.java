package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

}

