package com.example.digital_clothing_shop.Repositories;

import com.example.digital_clothing_shop.Models.Order;
import com.example.digital_clothing_shop.Models.OrderReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderReviewRepository extends JpaRepository<OrderReview, Integer> {

    List<OrderReview> findByOrder(Order order);
}
