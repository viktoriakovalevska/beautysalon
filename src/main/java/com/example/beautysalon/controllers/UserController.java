package com.example.beautysalon.controllers;

import com.example.beautysalon.common.LoginRequest;
import com.example.beautysalon.common.Response;
import com.example.beautysalon.common.Role;
import com.example.beautysalon.dto.UserRequestDTO;
import com.example.beautysalon.dto.UserResponseDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.services.AuthenticationService;
import com.example.beautysalon.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    private final SessionRepository<?> sessionRepository;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @SecurityRequirements()
    public Response<UserResponseDTO> login(@RequestBody LoginRequest loginRequest, HttpServletRequest servletRequest) {
        return Response.createSuccessfulResponseEntity(authenticationService.onAuthentication(loginRequest, servletRequest));
    }

    @PostMapping("/register")
    @SecurityRequirements()
    public Response<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) throws Exception{

        return Response.createSuccessfulResponseEntity(userService.addUser(userRequestDTO));
    }

    @GetMapping("admin/user/id/{id}")
    public Response<UserResponseDTO> findByUserId(@PathVariable(value = "id") Long id) {
        return Response.createSuccessfulResponseEntity(userService.findUserById(id));
    }
    @GetMapping("admin/user/phone/{phone}")
    public Response<UserResponseDTO> findByUserPhone(@PathVariable(value = "phone") String phone) {
        return Response.createSuccessfulResponseEntity(userService.findUserByPhone(phone));
    }

    @PutMapping("/user/edit")
    public Response<UserResponseDTO> userEdit(@RequestBody UserRequestDTO userEditDTO, HttpSession session) throws Exception{
        return Response.createSuccessfulResponseEntity(userService.editUser((Long) session.getAttribute(AuthenticationService.USER_ID), new UserResponseDTO(userEditDTO)));
    }

    @PutMapping("admin/user/id/{id}/edit")
    public Response<UserResponseDTO> editUserById(@PathVariable(value = "id") Long id, @RequestBody UserRequestDTO userEditDTO) throws Exception{
        return Response.createSuccessfulResponseEntity(userService.editUser(id, new UserResponseDTO(userEditDTO)));
    }
    @PutMapping("admin/user/id/{id}/roles/edit")
    public Response<UserResponseDTO> editUserRolesById(@PathVariable(value = "id") Long id, @RequestBody Set<Role> roles) throws Exception {
        return Response.createSuccessfulResponseEntity(userService.setRoles(id,roles));
    }
    @DeleteMapping("admin/user/id/{id}/delete")
    public Response<UserResponseDTO> removeUser(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(userService.removeUser(id));
    }
}

