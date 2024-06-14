package com.example.digital_clothing_shop.Security;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

import com.example.digital_clothing_shop.Models.Role;
import com.example.digital_clothing_shop.Models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); 

    @Value("${app.jwt.expirationMs}")
    private int jwtExpirationMs;

    public String generateToken(UserModel user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        
        String roles = user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(","));
        
        return Jwts.builder()
                .setSubject(Integer.toString(user.getId()))
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
        UserDetails userDetails = new User(claims.getSubject(), "", Collections.emptyList());
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}