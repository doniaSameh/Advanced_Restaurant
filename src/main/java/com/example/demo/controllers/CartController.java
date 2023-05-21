package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CartItem;
import com.example.demo.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void addItemToCart(@RequestBody CartItem item) {
        cartService.addItemToCart(item);
    }

    @PostMapping("/remove/{index}")
    public void removeItemFromCart(@PathVariable int index) {
        cartService.removeItemFromCart(index);
    }

    @PostMapping("/update/{index}")
    public void updateItemInCart(@PathVariable int index, @RequestBody CartItem item) {
        cartService.updateItemInCart(index, item);
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/checkout")
    public void checkout() {
        cartService.checkout();
        cartService.clearCart();
    }

    @PostMapping("/save")
    public void saveCartItems() {
        cartService.saveCartItems();
    }
}


