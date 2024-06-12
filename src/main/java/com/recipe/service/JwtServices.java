package com.recipe.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

public interface JwtServices {
    String generateToken(UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    String extractUserName(String token);

    Key getSignKey();

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
}
