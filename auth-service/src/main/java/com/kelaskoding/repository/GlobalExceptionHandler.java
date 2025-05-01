package com.kelaskoding.repository;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleMalformedJwt(MalformedJwtException e) {
        return "Invalid Token";
    }
}
