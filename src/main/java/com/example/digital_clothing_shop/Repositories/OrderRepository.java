package com.example.digital_clothing_shop.Repositories;

import com.example.digital_clothing_shop.Models.Order;
import com.example.digital_clothing_shop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
