package com.github.wirbelwind03.autosinistre.exception.handler;

import com.github.wirbelwind03.autosinistre.exception.BadCredentialsException;
import com.github.wirbelwind03.autosinistre.model.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadCredentials(BadCredentialsException e) {
        return ErrorResponseDTO.builder()
                .errorCode("BAD_CREDENTIALS_UNAUTHORIZED")
                .message(e.getMessage())
                .build();
    }
}
