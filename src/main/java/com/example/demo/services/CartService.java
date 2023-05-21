package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.repositories.CartItemRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private Cart cart;

    public void addItemToCart(CartItem item) {
        cart.addItem(item);
    }

    public void removeItemFromCart(int index) {
        cart.removeItem(index);
    }

    public void updateItemInCart(int index, CartItem item) {
        cart.updateItem(index, item);
    }

    public List<CartItem> getCartItems() {
        return cart.getItems();
    }

    public void clearCart(){
        cart.clear();
    }

    public void checkout() {
        // Perform checkout logic here
    }

    public void saveCartItems() {
        cartItemRepository.saveAll(cart.getItems());
    }
}

