package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    public void deleteByName(String name);
}
