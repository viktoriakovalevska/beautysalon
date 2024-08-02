package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected HttpEntity<Response<String>> doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return new HttpEntity<>(Response.createGeneralErrorResponseEntity(ex.getMessage()));
    }
}
