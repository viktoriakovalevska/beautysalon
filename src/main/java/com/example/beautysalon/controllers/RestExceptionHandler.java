package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    protected HttpEntity<Response<String>> doResolveException(WebRequest request, Exception ex) {
        return new HttpEntity<>(Response.createGeneralErrorResponseEntity(ex.getMessage()));
    }
}
