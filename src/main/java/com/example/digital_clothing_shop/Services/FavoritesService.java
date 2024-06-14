package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.Clothing;
import com.example.digital_clothing_shop.Models.Favorite;
import com.example.digital_clothing_shop.Models.UserModel;
import com.example.digital_clothing_shop.Repositories.ClothingRepository;
import com.example.digital_clothing_shop.Repositories.FavoriteRepository;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private UserRepository userRepository;

    public Favorite addToFavorites(Integer clothingId, String email){
        Clothing clothing = this.clothingRepository.findById(clothingId).get();
        UserModel user = this.userRepository.findByEmail(email).get();

        Favorite newFavorite = new Favorite();

        newFavorite.setClothing(clothing);
        newFavorite.setUser(user);
        return this.favoriteRepository.save(newFavorite);
    }

    public void deleteFavorite(Integer favoriteId){
        this.favoriteRepository.deleteById(favoriteId);
    }

    public List<Favorite> getUserFavorites(Integer userId){
        UserModel user = this.userRepository.findById(userId).get();
        return this.favoriteRepository.findByUser(user);
    }
}
