package com.github.wirbelwind03.autosinistre.exception.handler;

import com.github.wirbelwind03.autosinistre.exception.BadCredentialsException;
import com.github.wirbelwind03.autosinistre.exception.UserNotFoundException;
import com.github.wirbelwind03.autosinistre.exception.UserAlreadyExistException;
import com.github.wirbelwind03.autosinistre.model.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleUserNotFound(UserNotFoundException e) {
        return ErrorResponseDTO.builder()
                .errorCode("USER_NOT_FOUND")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadCredentials(BadCredentialsException e) {
        return ErrorResponseDTO.builder()
                .errorCode("BAD_CREDENTIALS_UNAUTHORIZED")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleUserAlreadyExistException(UserAlreadyExistException e) {
        return ErrorResponseDTO.builder()
                .errorCode("USER_ALREADY_EXIST_CONFLICT")
                .message(e.getMessage())
                .build();
    }
}
