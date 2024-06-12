package com.recipe.controller;

import com.recipe.dto.*;
import com.recipe.entity.User;
import com.recipe.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app.chat.api/auth")
public class AuthController {
    @Autowired
    private AuthServices authSerivices;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(authSerivices.signUp(signUpRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authSerivices.logIn(loginRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequest){
        return ResponseEntity.ok(authSerivices.refreshToken(refreshTokenRequest));
    }
}
