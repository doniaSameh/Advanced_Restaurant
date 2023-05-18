package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cart {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    User user;

    
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

    
}
