package com.example.beautysalon.controllers;

import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .birthDay(userDTO.getBirthDay())
                .roles(Set.of("USER"))
                .build();
        return userService.saveUser(user);
    }
}

