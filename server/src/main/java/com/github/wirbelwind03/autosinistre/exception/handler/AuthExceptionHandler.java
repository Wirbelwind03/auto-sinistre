package com.github.wirbelwind03.autosinistre.exception.handler;

import com.github.wirbelwind03.autosinistre.exception.BadCredentialsException;
import com.github.wirbelwind03.autosinistre.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFound(UserNotFoundException e) {
        return Map.of(
                "errorCode", "USER_NOT_FOUND",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleBadCredentials(BadCredentialsException e) {
        return Map.of(
                "errorCode", "BAD_CREDENTIALS_UNAUTHORIZED",
                "message", e.getMessage()
        );
    }
}
