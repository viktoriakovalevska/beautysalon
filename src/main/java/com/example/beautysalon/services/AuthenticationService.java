package com.example.beautysalon.services;

import com.example.beautysalon.common.LoginRequest;
import com.example.beautysalon.dto.UserResponseDTO;
import com.example.beautysalon.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService{
    public static final String USER_ID = "userId";
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    public UserResponseDTO onAuthentication(LoginRequest loginRequest, HttpServletRequest request){
        UserResponseDTO result;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);


        HttpSession session = request.getSession();
        result = new UserResponseDTO(userRepository.findByUsername(authentication.getName()));
        session.setAttribute(USER_ID, result.getId());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,  SecurityContextHolder.getContext());
        ;
        log.info("authentication details: {}", authentication.getDetails());
        log.info("Authentication successful for user: {}", loginRequest.getUsername());
        log.info("Authorities: {}", authentication.getAuthorities());
        log.info("Session ID: {}", session.getId());
        log.info("Session token: {}", new String(Base64.getEncoder().encode(session.getId().getBytes(StandardCharsets.UTF_8))));
        return result;
    }
}
