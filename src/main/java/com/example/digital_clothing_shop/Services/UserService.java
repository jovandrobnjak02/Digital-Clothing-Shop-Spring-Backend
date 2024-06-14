package com.example.digital_clothing_shop.Services;

import com.example.digital_clothing_shop.Models.UserModel;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import com.example.digital_clothing_shop.Requests.RegisterRequest;

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
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public UserModel registerUser(RegisterRequest user) {

        UserModel newUser = new UserModel();
        newUser.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        return userRepository.save(newUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
