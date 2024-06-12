package com.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app.chat.api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("User logged In");
    }

}
