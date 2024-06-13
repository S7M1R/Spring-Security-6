package com.recipe.service.ServicesIMPL;

import com.recipe.entity.User;
import com.recipe.repository.UserRepository;
import com.recipe.service.JwtServices;
import com.recipe.service.UserServices;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServicesIMPL implements UserServices {

    private final UserRepository userRepository;
    private final JwtServices jwtServices;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found !!!"));
    }

    @Override
    public User findUserByJwt(String jwtToken) {
        String token = jwtToken.substring(7);
        System.out.println(token);
        String userName = jwtServices.extractUserName(token);
        UserDetails userDetails = this.loadUserByUsername(userName);
        boolean tokenValid = jwtServices.isTokenValid(token, userDetails);
        if (!tokenValid){
            return null;
        }
        Optional<com.recipe.entity.User> byUserName = userRepository.findByUserName(userName);
        com.recipe.entity.User user = byUserName.get();
        return user;
    }
}
