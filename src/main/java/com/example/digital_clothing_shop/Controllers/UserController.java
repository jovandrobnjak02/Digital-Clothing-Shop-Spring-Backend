package com.example.digital_clothing_shop.Controllers;

import com.example.digital_clothing_shop.Models.Favorite;
import com.example.digital_clothing_shop.Models.UserModel;
import com.example.digital_clothing_shop.Security.JwtUtils;
import com.example.digital_clothing_shop.Services.FavoritesService;
import com.example.digital_clothing_shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Integer userId) {
        Optional<UserModel> user = userService.getOne(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<UserModel> getUserByEmail(@RequestParam String email) {
        Optional<UserModel> user = userService.getByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel user) {
        if (userService.getByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Optional<UserModel> userOpt = userService.getByEmail(email);

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        UserModel user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
        }

        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer userId, @RequestBody UserModel userDetails) {
        Optional<UserModel> optionalUser = userService.getOne(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setAddress(userDetails.getAddress());
            user.setPasswordHash(userDetails.getPasswordHash());
            UserModel updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Favorite>> getUserFavorites(@PathVariable Integer userId) {
        List<Favorite> favorites = favoritesService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/favorites/{favoriteId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Integer favoriteId) {
        favoritesService.deleteFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }
}

