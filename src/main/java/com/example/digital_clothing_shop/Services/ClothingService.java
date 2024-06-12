package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Repositories.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;

    public List<Clothing> getAllClothes(){
        return this.clothingRepository.findAll();
    }
}
