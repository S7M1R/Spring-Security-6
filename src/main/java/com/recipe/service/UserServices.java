package com.recipe.service;

import com.recipe.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);

    public User findUserByJwt(String token);
}
