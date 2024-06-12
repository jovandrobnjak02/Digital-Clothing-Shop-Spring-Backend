package com.example.digital_clothing_shop.Repositories;


import com.example.digital_clothing_shop.Models.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Integer> {

}
