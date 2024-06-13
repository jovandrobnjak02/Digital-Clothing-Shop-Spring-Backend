package com.example.digital_clothing_shop.Models;

import com.example.digital_clothing_shop.Enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User orderingUser;

    @ManyToMany
    @JoinTable(
            name = "order_clothing",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "clothing_id")
    )
    private List<Clothing> orderedClothes;

    public Order(Long id, OrderStatus status, Double amount, User orderingUser, List<Clothing> orderedClothes) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.orderingUser = orderingUser;
        this.orderedClothes = orderedClothes;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getOrderingUser() {
        return orderingUser;
    }

    public void setOrderingUser(User orderingUser) {
        this.orderingUser = orderingUser;
    }

    public List<Clothing> getOrderedClothes() {
        return orderedClothes;
    }

    public void setOrderedClothes(List<Clothing> orderedClothes) {
        this.orderedClothes = orderedClothes;
    }
}
