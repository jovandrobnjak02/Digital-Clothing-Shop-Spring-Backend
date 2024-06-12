package com.example.digital_clothing_shop.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "clothing_colors")
public class ClothingColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "clothing_id", nullable = false)
    private Clothing clothing;

    @Column(name = "image_src", nullable = false)
    private String imageSrc;

    public ClothingColor(Long id, String color, Clothing clothing, String imageSrc) {
        this.id = id;
        this.color = color;
        this.clothing = clothing;
        this.imageSrc = imageSrc;
    }

    public ClothingColor() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}

