package com.example.beautysalon.controllers;

import com.example.beautysalon.config.CustomAuthenticationSuccessHandler;
import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    private final SessionRepository<?> sessionRepository;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .birthDay(userDTO.getBirthDay())
                .roles(Set.of("USER"))
                .build();
        return userService.saveUser(user);
    }

    @GetMapping("/user/id/{id}")
    public UserDTO findByUserID(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }
    @PostMapping("/user/edit")
    public UserDTO userEdit(@RequestBody UserDTO userDTO, HttpSession session) {

        return userService.editUser((Long) session.getAttribute(CustomAuthenticationSuccessHandler.USER_ID), userDTO);
    }


}

