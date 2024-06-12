package com.recipe.service;

import com.recipe.dto.*;
import com.recipe.entity.User;

public interface AuthServices {
    User signUp(SignUpRequestDto signUpRequestDto);

    JwtAuthResponseDto logIn(LoginRequestDto loginRequestDto);

    JwtAuthResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest);
}
