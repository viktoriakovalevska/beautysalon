package com.example.beautysalon.controllers;

import com.example.beautysalon.common.LoginRequest;
import com.example.beautysalon.services.AuthenticationService;
import com.example.beautysalon.common.Response;
import com.example.beautysalon.common.Role;
import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    private final SessionRepository<?> sessionRepository;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @SecurityRequirements()
    public Response<UserDTO> login(@RequestBody LoginRequest loginRequest, HttpServletRequest servletRequest) {
        return Response.createSuccessfulResponseEntity(authenticationService.onAuthentication(loginRequest, servletRequest));
    }

    @PostMapping("/register")
    public Response<UserDTO> register(@RequestBody UserDTO userDTO) throws Exception{
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .birthDay(userDTO.getBirthDay())
                .roles(Set.of(Role.USER))
                .build();
        return Response.createSuccessfulResponseEntity(userService.saveUser(user));
    }

    @GetMapping("admin/user/id/{id}")
    public Response<UserDTO> findByUserId(@PathVariable(value = "id") Long id) {
        return Response.createSuccessfulResponseEntity(userService.findUserById(id));
    }
    @GetMapping("admin/user/phone/{phone}")
    public Response<UserDTO> findByUserPhone(@PathVariable(value = "phone") String phone) {
        return Response.createSuccessfulResponseEntity(userService.findUserByPhone(phone));
    }

    @PostMapping("/user/edit")
    public Response<UserDTO> userEdit(@RequestBody UserDTO userDTO, HttpSession session) throws Exception{
        return Response.createSuccessfulResponseEntity(userService.editUser((Long) session.getAttribute(AuthenticationService.USER_ID), userDTO));
    }

    @PostMapping("admin/user/id/{id}/edit")
    public Response<UserDTO> editUserById(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) throws Exception{
        return Response.createSuccessfulResponseEntity(userService.editUser(id, userDTO));
    }
    @PostMapping("admin/user/id/{id}/roles/edit")
    public Response<UserDTO> editUserRolesById(@PathVariable(value = "id") Long id, @RequestBody Set<Role> roles) throws Exception {
        return Response.createSuccessfulResponseEntity(userService.setRoles(id,roles));
    }
    @DeleteMapping("admin/user/id/{id}/delete")
    public Response<UserDTO> removeUser(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(userService.removeUser(id));
    }

    @ExceptionHandler
    public Response<String> errorHandler(Exception ex, WebRequest request){
        return Response.createGeneralErrorResponseEntity(ex.getMessage());
    }
}

