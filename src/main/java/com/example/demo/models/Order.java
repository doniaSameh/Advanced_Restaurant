package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String billNo;
    private String menu;
    private int quantity;
    private String orderedTime;

    public Order(Long id, String billNo, String menu, int quantity, String orderedTime) {
        this.id = id;
        this.billNo = billNo;
        this.menu = menu;
        this.quantity = quantity;
        this.orderedTime = orderedTime;
    }
    public Order(String billNo, String menu, int quantity){
        this.billNo = billNo;
        this.menu = menu;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBillNo() {
        return billNo;
    }
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getOrderedTime() {
        return orderedTime;
    }
    public void setOrderedTime(String orderedTime) {
        this.orderedTime = orderedTime;
    }
   
    
}
