package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class FoodItemService {
    public void log(String data){
        System.out.println("LOGGER: "+data);
    }
}
