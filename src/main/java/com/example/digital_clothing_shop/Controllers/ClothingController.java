package com.example.digital_clothing_shop.Controllers;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.ClothingReview;
import com.example.digital_clothing_shop.Requests.ClothingReviewRequest;
import com.example.digital_clothing_shop.Services.ClothingReviewService;
import com.example.digital_clothing_shop.Services.ClothingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @Autowired
    private ClothingReviewService clothingReviewService;


    @GetMapping
    public List<Clothing> getAllClothes(){
        return this.clothingService.getAllClothes();
    }

    @GetMapping("/reviews")
    public List<ClothingReview> getReviewsForClothing(@RequestParam int clothingId){
        return this.clothingReviewService.getReviewsForClothing(clothingId);
    }
    @GetMapping("/reviews/review")
    public ClothingReview getSpecificReview(@RequestParam int reviewId){
        return this.clothingReviewService.getSpecificReview(reviewId).get();
    }
    @PostMapping("/reviews/add")
    public ResponseEntity<ClothingReview> createReview(@RequestBody ClothingReviewRequest review){
        return ResponseEntity.ok(this.clothingReviewService.createReview(review));
    }

    @PutMapping("/reviews/edit")
    public ResponseEntity<ClothingReview> editReview(@PathVariable int reviewId, @RequestBody ClothingReviewRequest review){
        return this.clothingReviewService.editReview(review ,reviewId);
    }

    @DeleteMapping("/review/delete")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId){
        this.clothingReviewService.deleteReview(reviewId);

        return ResponseEntity.ok().<Void>build();
    }


    @GetMapping("/{clothingId}")
    public Optional<Clothing> getOne(@PathVariable int clothingId){
        return this.clothingService.getOne(clothingId);
    }

    @GetMapping("/name/{itemName}")
    public Clothing getByName(@PathVariable String itemName){
        return this.clothingService.getByName(itemName);
    }
    @GetMapping("/type/{type}")
    public List<Clothing> getByType(@PathVariable String type){
        return this.clothingService.getByType(type);
    }
    @GetMapping("/manufacturer/{manufacturer}")
    public List<Clothing> getByManufacturer(@PathVariable String manufacturer){
        return this.clothingService.getByManufacturer(manufacturer);
    }
}
