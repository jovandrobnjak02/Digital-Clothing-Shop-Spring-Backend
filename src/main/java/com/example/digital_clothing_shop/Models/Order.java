package com.example.digital_clothing_shop.Models;

import com.example.digital_clothing_shop.Enums.OrderStatus;
import jakarta.persistence.*;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "user_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Order> orderingUsers;

    @ManyToMany
    @JoinTable(
            name = "order_clothing",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "clothing_id")
    )
    private Set<Order> orderedClothes;

    public Order(Long id, OrderStatus status, Double amount, Set<Order> orderingUsers, Set<Order> orderedClothes) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.orderingUsers = orderingUsers;
        this.orderedClothes = orderedClothes;
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

    public Set<Order> getOrderingUsers() {
        return orderingUsers;
    }

    public void setOrderingUsers(Set<Order> orderingUsers) {
        this.orderingUsers = orderingUsers;
    }

    public Set<Order> getOrderedClothes() {
        return orderedClothes;
    }

    public void setOrderedClothes(Set<Order> orderedClothes) {
        this.orderedClothes = orderedClothes;
    }
}
