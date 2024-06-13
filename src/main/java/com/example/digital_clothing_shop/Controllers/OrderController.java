package com.example.digital_clothing_shop.Controllers;


import com.example.digital_clothing_shop.Enums.OrderStatus;
import com.example.digital_clothing_shop.Models.Order;
import com.example.digital_clothing_shop.Requests.CreateOrderRequest;
import com.example.digital_clothing_shop.Services.OrderReviewService;
import com.example.digital_clothing_shop.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderReviewService orderReviewService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUsersOrders(@PathVariable Integer userId) {
        return orderService.getUsersOrders(userId);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest data) {
        orderService.createOrder(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> editOrder(@RequestBody CreateOrderRequest data, @PathVariable Integer orderId) {
        return orderService.editOrder(data, orderId);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Order> updateStatus(@PathVariable Integer orderId, @RequestBody OrderStatus status) {
        return orderService.updateStatus(orderId, status);
    }
}
