package com.recipe.dto;

import lombok.Data;

@Data
public class JwtAuthResponseDto {
    private String token;
    private String refreshToken;
}
