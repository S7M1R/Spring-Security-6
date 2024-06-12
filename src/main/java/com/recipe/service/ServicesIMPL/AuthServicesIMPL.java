package com.recipe.service.ServicesIMPL;

import com.recipe.dto.*;
import com.recipe.entity.User;
import com.recipe.repository.UserRepository;
import com.recipe.service.AuthServices;
import com.recipe.service.JwtServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServicesIMPL implements AuthServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServices jwtService;

    @Override
    public User signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setFullName(signUpRequestDto.getFullName());
        user.setUserName(signUpRequestDto.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthResponseDto logIn(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));
        var user = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(()-> new IllegalArgumentException("Invalid UserName"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setToken(jwt);
        jwtAuthResponseDto.setRefreshToken(refreshToken);
        return jwtAuthResponseDto;
    }

    @Override
    public JwtAuthResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        String userName = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByUserName(userName).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
            jwtAuthResponseDto.setToken(jwt);
            jwtAuthResponseDto.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponseDto;
        }
        return null;
    }
}
