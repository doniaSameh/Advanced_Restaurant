package com.example.demo.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CartItem {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Cart cart;

    @OneToOne
    FoodItem items;

    public CartItem() {
        
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Cart getCart() {
        return cart;
    }


    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public FoodItem getItems() {
        return items;
    }


    public void setItems(FoodItem items) {
        this.items = items;
    }

}
