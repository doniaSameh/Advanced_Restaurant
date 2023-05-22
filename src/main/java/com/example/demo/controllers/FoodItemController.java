package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import com.example.demo.models.FoodItem;
import com.example.demo.repositories.FoodItemRepository;
import com.example.demo.services.FoodItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("")
    public ResponseEntity getFoodItems(){
        foodItemService.log("User fetched all orders");
        List<FoodItem> orders=this.foodItemRepository.findAll();
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    // This method handles GET requests to the "/orders/{OrderId}" endpoint 
    // and takes a path variable OrderId to specify which Order to return. 
    // It loops through the list of orders and returns the Order with the specified ID if it exists,
    // along with an HTTP status of 200 OK. 
    // If no Order is found, it returns an HTTP status of 404 Not Found.
    @GetMapping("{foodItemId}")
    public ResponseEntity getFoodItem(@PathVariable("foodItemId") Integer foodItemId){
        FoodItem foodItem=this.foodItemRepository.findById(foodItemId).orElse(null);
        if(foodItem==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foodItem, HttpStatus.OK);
    }

    public ResponseEntity getAllFoodItem(){

        List<FoodItem> foodItem = this.foodItemRepository.findAll();

        ResponseEntity response = new ResponseEntity<>(foodItem, HttpStatus.OK);
        return response;
    }
    
    @PostMapping("")
    public ResponseEntity createFoodItem(@RequestBody Map<String,String> body){
        // try {
            FoodItem foodItem = new FoodItem();
            foodItem.setName(body.get("name"));
            foodItem.setPrice(0);
            this.foodItemRepository.save(foodItem);
            ResponseEntity response = new ResponseEntity<>(foodItem, HttpStatus.OK);
            return response;    
        // }
        // catch (ParseException e) {
            
        //     return ResponseEntity.badRequest().build();
        // }
    }
    @PutMapping("{foodItemId}")
    public ResponseEntity updateOrder(@PathVariable("foodItemId") Integer foodItemId, @RequestBody Map<String,String> body){
        FoodItem foodItem=this.foodItemRepository.findById(foodItemId).orElse(null);
        if(foodItem==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
        //Order order = new Order(body.get("billNo"), body.get("menu"), Integer.parseInt(body.get("quantity")));
        foodItem.setName(body.get("name"));
        foodItem.setPrice(0);
        this.foodItemRepository.save(foodItem);
        return new ResponseEntity(foodItem, HttpStatus.OK);

    }
    @DeleteMapping("{foodItemId}")
    public ResponseEntity deleteOrder(@PathVariable("foodItemId") Integer foodItemId){
        FoodItem foodItem=this.foodItemRepository.findById(foodItemId).orElse(null);
        if(foodItem==null)
        {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
   
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }
}
