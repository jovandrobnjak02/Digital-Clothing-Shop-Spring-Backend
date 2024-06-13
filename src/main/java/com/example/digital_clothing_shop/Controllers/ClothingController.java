package com.example.digital_clothing_shop.Controllers;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.ClothingReview;
import com.example.digital_clothing_shop.Models.Favorite;
import com.example.digital_clothing_shop.Requests.ClothingReviewRequest;
import com.example.digital_clothing_shop.Services.ClothingReviewService;
import com.example.digital_clothing_shop.Services.ClothingService;
import com.example.digital_clothing_shop.Services.FavoritesService;
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

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping
    public List<Clothing> getAllClothes(){
        return this.clothingService.getAllClothes();
    }

    @GetMapping("/{clothingId}/reviews")
    public List<ClothingReview> getReviewsForClothing(@PathVariable int clothingId){
        return this.clothingReviewService.getReviewsForClothing(clothingId);
    }
    @GetMapping("/{clothingId}/review/{reviewId}")
    public ClothingReview getSpecificReview(@PathVariable int reviewId){
        return this.clothingReviewService.getSpecificReview(reviewId).get();
    }
    @PostMapping("/{clothingId}/review")
    public ResponseEntity<ClothingReview> createReview(@RequestBody ClothingReviewRequest review, @PathVariable int clothingId){
        review.setClothingId(clothingId);
        return ResponseEntity.ok(this.clothingReviewService.createReview(review));
    }

    @PutMapping("/{clothingId}/review/{reviewId}")
    public ResponseEntity<ClothingReview> editReview(@PathVariable int reviewId, @RequestBody ClothingReviewRequest review){
        return this.clothingReviewService.editReview(reviewId ,review);
    }

    @DeleteMapping("/{clothingId}/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId){
        this.clothingReviewService.deleteReview(reviewId);

        return ResponseEntity.ok().<Void>build();
    }

    @GetMapping("/{clothingId}")
    public Optional<Clothing> getOne(@PathVariable int clothingId){
        return this.clothingService.getOne(clothingId);
    }

    @GetMapping("/{itemName}")
    public Clothing getByName(@PathVariable String itemName){
        return this.clothingService.getByName(itemName);
    }
    @GetMapping("/{type}")
    public List<Clothing> getByType(@PathVariable String type){
        return this.clothingService.getByType(type);
    }
    @GetMapping("/{manufacturer}")
    public List<Clothing> getByManufacturer(@PathVariable String manufacturer){
        return this.clothingService.getByManufacturer(manufacturer);
    }

    @PostMapping("/{clothingId}/favorite")
    public Favorite addToFavorites(@PathVariable Integer clothingId, Integer userId){
        return this.favoritesService.addToFavorites(clothingId, userId);
    }
}
