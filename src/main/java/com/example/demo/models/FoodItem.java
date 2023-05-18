package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
    private int price;

    public FoodItem() {
    }

    public FoodItem(int id, String name, String description, String image, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodItem id(int id) {
        setId(id);
        return this;
    }

    public FoodItem name(String name) {
        setName(name);
        return this;
    }

    public FoodItem description(String description) {
        setDescription(description);
        return this;
    }

    public FoodItem image(String image) {
        setImage(image);
        return this;
    }

    public FoodItem price(int price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FoodItem)) {
            return false;
        }
        FoodItem menu = (FoodItem) o;
        return id == menu.id && Objects.equals(name, menu.name) && Objects.equals(description, menu.description) && Objects.equals(image, menu.image) && price == menu.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image, price);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }
    
}
