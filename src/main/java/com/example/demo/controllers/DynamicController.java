package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.FoodItem;
import com.example.demo.repositories.FoodItemRepository;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicController {
    @Autowired
    FoodItemRepository foodItemRepository;


    @GetMapping("")
    public String index(){
        return "index.html";
    }
    @GetMapping("/items")
    public ModelAndView listItems(){
        ModelAndView mav = new ModelAndView("list-items.html");
        List<FoodItem> foodItems = this.foodItemRepository.findAll();
        mav.addObject("items", foodItems);
        return mav;
    }
    @GetMapping("/create-item")
    public ModelAndView getCreateItemForm(){
        ModelAndView mav = new ModelAndView("add-item-form.html");
        mav.addObject("item", new FoodItem());
        return mav;
    }

    @PostMapping("/save-item")
    public String saveItem(@ModelAttribute() FoodItem foodItem){
        this.foodItemRepository.save(foodItem);
        return "redirect:/thymeleaf/items";

    }
}
