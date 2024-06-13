package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.User;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> getOne(Integer userId){
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
