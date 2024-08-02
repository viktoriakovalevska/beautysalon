package com.example.beautysalon.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    @SecurityRequirements()
    public String home(){
        return "Wellcome";
    }
}
