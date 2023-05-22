package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import javax.persistence.OneToMany;

@Entity
public class Cart {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    User user;

    @OneToMany(mappedBy="cart")
    private List<CartItem> items;

    
    public Cart() {
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public void updateItem(int index, CartItem item) {
        items.set(index, item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}

    

