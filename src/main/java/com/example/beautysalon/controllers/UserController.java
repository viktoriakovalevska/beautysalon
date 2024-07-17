package com.example.beautysalon.controllers;

import com.example.beautysalon.config.CustomAuthenticationSuccessHandler;
import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    private final SessionRepository<?> sessionRepository;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) throws Exception{
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .birthDay(userDTO.getBirthDay())
                .roles(Set.of("USER"))
                .build();
        return userService.saveUser(user);
    }

    @GetMapping("admin/user/id/{id}")
    public UserDTO findByUserId(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }
    @GetMapping("admin/user/phone/{phone}")
    public UserDTO findByUserPhone(@PathVariable(value = "phone") String phone) {
        return userService.findUserByPhone(phone);
    }

    @PostMapping("/user/edit")
    public UserDTO userEdit(@RequestBody UserDTO userDTO, HttpSession session) throws Exception{
        return userService.editUser((Long) session.getAttribute(CustomAuthenticationSuccessHandler.USER_ID), userDTO);
    }

    @PostMapping("admin/user/id/{id}/edit")
    public UserDTO editUserById(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) throws Exception{
        return userService.editUser(id, userDTO);
    }

}

