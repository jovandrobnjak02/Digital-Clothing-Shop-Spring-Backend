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
            name = "clothing_sizes",
            joinColumns = @JoinColumn(name = "clothing_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> clothingSizes;

    @OneToMany(mappedBy = "clothing", cascade = CascadeType.ALL)
    private Set<ClothingColor> colors;

    public Clothing() {
    }

    public Clothing(Long id, String itemName, Double price, String manufacturer, String type, Date createdAt, Set<Size> clothingSizes, Set<ClothingColor> colors) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.manufacturer = manufacturer;
        this.type = type;
        this.createdAt = createdAt;
        this.clothingSizes = clothingSizes;
        this.colors = colors;
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

    public Set<Size> getClothingSizes() {
        return clothingSizes;
    }

    public void setClothingSizes(Set<Size> clothingSizes) {
        this.clothingSizes = clothingSizes;
    }

    public Set<ClothingColor> getColors() {
        return colors;
    }

    public void setColors(Set<ClothingColor> colors) {
        this.colors = colors;
    }
}
