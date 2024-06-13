package com.example.digital_clothing_shop.Repositories;

import com.example.digital_clothing_shop.Models.Favorite;
import com.example.digital_clothing_shop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);
}

