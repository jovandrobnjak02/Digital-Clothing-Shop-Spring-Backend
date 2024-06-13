package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.Order;
import com.example.digital_clothing_shop.Models.OrderReview;
import com.example.digital_clothing_shop.Repositories.OrderRepository;
import com.example.digital_clothing_shop.Repositories.OrderReviewRepository;
import com.example.digital_clothing_shop.Requests.OrderReviewRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderReviewService {
    @Autowired
    private OrderReviewRepository orderReviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderReview> getReviewsForOrders(Integer id){
        Optional<Order> item = this.orderRepository.findById(id);
        if(item.isPresent()){
            return this.orderReviewRepository.findByOrder(item.get());
        }
        return null;
    }

    public OrderReview getSpecificReview(int id){
        return this.orderReviewRepository.findById(id).get();
    }


    public OrderReview createReview(OrderReviewRequest data){
        Order order = this.orderRepository.findById(data.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + data.getOrderId()));
        OrderReview newReview = new OrderReview();
        newReview.setComment(data.getComment());
        newReview.setGrade(data.getGrade());
        newReview.setOrder(order);

        return this.orderReviewRepository.save(newReview);
    }

    public ResponseEntity<OrderReview> editReview(OrderReviewRequest data){
        return (this.orderReviewRepository.findById(data.getOrderId())).map((review) -> {
            review.setComment(data.getComment());
            review.setGrade(data.getGrade());
            return ResponseEntity.ok(orderReviewRepository.save(review));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteReview(int reviewId){
        this.orderReviewRepository.deleteById(reviewId);
    }
}
