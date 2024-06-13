package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Repositories.ClothingRepository;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;



    @Autowired
    private UserRepository userRepository;

    public List<Clothing> getAllClothes(){
        return this.clothingRepository.findAll();
    }

    public List<Clothing> getByType(String type){
        return this.clothingRepository.findByType(type);
    }
    public Clothing getByName(String itemName){
        return this.clothingRepository.findByItemName(itemName);
    }
    public List<Clothing> getByManufacturer(String manufacturer){
        return this.clothingRepository.findByManufacturer(manufacturer);
    }

    public Optional<Clothing> getOne(int clothingId){
        return this.clothingRepository.findById(clothingId);
    }

}
