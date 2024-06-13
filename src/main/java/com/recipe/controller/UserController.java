package com.recipe.controller;

import com.recipe.entity.User;
import com.recipe.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app.chat.api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    @GetMapping
    public ResponseEntity<User> userProfile(@RequestHeader("Authorization") String token) {
        if (StringUtils.isEmpty(token)) {
            return ResponseEntity.badRequest().build(); // or return an error response
        }
        User user = userServices.findUserByJwt(token);
        return ResponseEntity.ok(user);
    }

}
