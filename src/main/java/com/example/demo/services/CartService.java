package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired 
    private CartRepository cartRepository;


    public void addItemToCart(Cart cart, CartItem item) {
        cart.addItem(item);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Cart cart,int index) {
        cart.removeItem(index);
    }

    public void updateItemInCart(Cart cart,int index, CartItem item) {
        cart.updateItem(index, item);
    }

    public List<CartItem> getCartItems(Cart cart) {
        return cart.getItems();
    }

    public void clearCart(Cart cart){
        cart.clear();
    }

    public void checkout(Cart cart) {
        // Perform checkout logic here
    }

    public void saveCartItems(Cart cart) {
        cartItemRepository.saveAll(cart.getItems());
    }
}

