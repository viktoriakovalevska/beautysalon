package com.example.beautysalon.services;

import com.example.beautysalon.common.LoginRequest;
import com.example.beautysalon.common.Role;
import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    public static final String USER_ID = "userId";
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    public UserDTO onAuthentication(LoginRequest loginRequest, HttpServletRequest request){
        UserDTO result;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);


        HttpSession session = request.getSession();
        result = new UserDTO(userRepository.findByUsername(authentication.getName()));
//        session.setAttribute(USER_ID, result.getId());
        log.info("authentication details: {}", authentication.getDetails());
        log.info("Authentication successful for user: {}", loginRequest.getUsername());
        log.info("Authorities: {}", authentication.getAuthorities());
        log.info("Session ID: {}", session.getId());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return result;
    }
}
