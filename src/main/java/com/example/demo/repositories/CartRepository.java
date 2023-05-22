package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;
import com.example.demo.models.User;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    public Optional<Cart> findByUser(User user);

}
