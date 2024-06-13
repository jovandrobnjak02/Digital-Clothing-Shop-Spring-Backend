package com.example.digital_clothing_shop.Controllers;


import com.example.digital_clothing_shop.Models.Token;
import com.example.digital_clothing_shop.Models.User;
import com.example.digital_clothing_shop.Repositories.TokenRepository;
import com.example.digital_clothing_shop.Repositories.UserRepository;
import com.example.digital_clothing_shop.Requests.LoginRequest;
import com.example.digital_clothing_shop.Responses.LoginResponse;
import com.example.digital_clothing_shop.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  TokenRepository tokenRepository;



    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var optUser = userRepository.findByEmail(request.getEmail());
        if(optUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .get();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        var response = new LoginResponse();
        response.setToken(jwtToken);
        response.setRefreshToken(refreshToken);
        response.setUserId(String.valueOf(user.getId()));
        response.setEmail(user.getEmail());
        response.setUserLastName(user.getLastName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = new Token();
        token.user = user;
        token.token = jwtToken;
        token.expired = false;
        token.revoked = false;

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
