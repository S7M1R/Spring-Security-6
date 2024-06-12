package com.recipe.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String fullName;
    private String userName;
    private String password;
}
