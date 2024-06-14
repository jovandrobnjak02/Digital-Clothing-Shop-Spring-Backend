package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.UserModel;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserModel> getOne(Integer userId){
        return userRepository.findById(userId);
    }

    public UserModel saveUser(UserModel user) {
       String newPassword = passwordEncoder.encode(user.getPasswordHash());
       user.setPasswordHash(newPassword);
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
