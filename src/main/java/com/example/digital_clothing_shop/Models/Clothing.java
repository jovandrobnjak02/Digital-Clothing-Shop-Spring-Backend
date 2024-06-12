package com.example.digital_clothing_shop.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="clothing")
public class Clothing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,name = "item_name")
    private String itemName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;


    @ManyToMany
    @JoinTable(
            name = "clothing_size",
            joinColumns = @JoinColumn(name = "clothing_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Order> clothingSizes;

    @ManyToMany
    @JoinTable(
            name = "order_clothing",
            joinColumns = @JoinColumn(name = "clothing_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> clothingOrders;

    public Clothing(Long id, String itemName, Double price, String manufacturer, String type, Date createdAt, Set<Order> clothingSizes, Set<Order> clothingOrders) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.manufacturer = manufacturer;
        this.type = type;
        this.createdAt = createdAt;
        this.clothingSizes = clothingSizes;
        this.clothingOrders = clothingOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Order> getClothingSizes() {
        return clothingSizes;
    }

    public void setClothingSizes(Set<Order> clothingSizes) {
        this.clothingSizes = clothingSizes;
    }

    public Set<Order> getClothingOrders() {
        return clothingOrders;
    }

    public void setClothingOrders(Set<Order> clothingOrders) {
        this.clothingOrders = clothingOrders;
    }
}
