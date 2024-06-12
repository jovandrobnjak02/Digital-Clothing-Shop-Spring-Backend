package com.example.digital_clothing_shop.Repositories;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.ClothingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothingReviewRepository extends JpaRepository<ClothingReview, Integer> {

    List<ClothingReview> findByClothing (Optional<Clothing> clothing);
}
