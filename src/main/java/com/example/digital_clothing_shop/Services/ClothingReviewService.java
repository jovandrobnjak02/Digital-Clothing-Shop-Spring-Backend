package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.ClothingReview;
import com.example.digital_clothing_shop.Repositories.ClothingRepository;
import com.example.digital_clothing_shop.Repositories.ClothingReviewRepository;
import com.example.digital_clothing_shop.Requests.ClothingReviewRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothingReviewService {

    @Autowired
    private ClothingReviewRepository clothingReviewRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    public List<ClothingReview> getReviewsForClothing(Integer id){
        Optional<Clothing> item = this.clothingRepository.findById(id);
        if(item.isPresent()){
            return this.clothingReviewRepository.findByClothing(item.get());
        }
        return null;
    }

    public Optional<ClothingReview> getSpecificReview(int id){
        return this.clothingReviewRepository.findById(id);
    }


    public ClothingReview createReview(ClothingReviewRequest data){
        Clothing clothing = this.clothingRepository.findById(data.getClothingId())
                .orElseThrow(() -> new EntityNotFoundException("Clothing not found with id: " + data.getClothingId()));
        ClothingReview newReview = new ClothingReview();
        newReview.setComment(data.getComment());
        newReview.setGrade(data.getGrade());
        newReview.setClothing(clothing);

        return this.clothingReviewRepository.save(newReview);
    }

    public ResponseEntity<ClothingReview> editReview(ClothingReviewRequest data, Integer reviewId){
        return (this.clothingReviewRepository.findById(reviewId)).map((review) -> {
            review.setComment(data.getComment());
            review.setGrade(data.getGrade());
            return ResponseEntity.ok(clothingReviewRepository.save(review));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteReview(int reviewId){
        this.clothingReviewRepository.deleteById(reviewId);
    }
}
