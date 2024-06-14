package com.example.digital_clothing_shop.Services;


import com.example.digital_clothing_shop.Enums.OrderStatus;
import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.Order;
import com.example.digital_clothing_shop.Models.UserModel;
import com.example.digital_clothing_shop.Repositories.ClothingRepository;
import com.example.digital_clothing_shop.Repositories.OrderRepository;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import com.example.digital_clothing_shop.Requests.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public List<Order> getUsersOrders(Integer userId){
        Optional<UserModel> user = this.userRepository.findById(userId);

        if(user.isPresent()){
            return this.orderRepository.findByOrderingUser(user.get());
        }else{
            return null;
        }
    }

    public void createOrder(CreateOrderRequest data){
        Order newOrder = new Order();
        List<Clothing> clothing = this.clothingRepository.findAllById(data.getClothingIds());
        Double amount = clothing.stream()
                .mapToDouble(Clothing::getPrice)
                .sum();
        newOrder.setOrderedClothes(clothing);
        newOrder.setAmount(amount);

        this.orderRepository.save(newOrder);
    }

    public void deleteOrder(Integer orderId){
        this.orderRepository.deleteById(orderId);
    }

    public ResponseEntity<Order> editOrder(CreateOrderRequest data, Integer orderId){
        return (this.orderRepository.findById(orderId)).map((order) -> {
            List<Clothing> clothing = this.clothingRepository.findAllById(data.getClothingIds());
            Double amount = clothing.stream()
                    .mapToDouble(Clothing::getPrice)
                    .sum();
            order.setOrderedClothes(clothing);
            order.setAmount(amount);
            return org.springframework.http.ResponseEntity.ok(orderRepository.save(order));
        }).orElseGet(() -> org.springframework.http.ResponseEntity.notFound().build());
    }

    public ResponseEntity<Order> updateStatus(Integer orderId, OrderStatus status){
        return (this.orderRepository.findById(orderId)).map((order) -> {

            order.setStatus(status);
            return org.springframework.http.ResponseEntity.ok(orderRepository.save(order));
        }).orElseGet(() -> org.springframework.http.ResponseEntity.notFound().build());
    }
}
