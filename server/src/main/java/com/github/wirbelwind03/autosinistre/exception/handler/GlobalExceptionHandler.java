package com.github.wirbelwind03.autosinistre.exception.handler;

import com.github.wirbelwind03.autosinistre.exception.AlreadyExistException;
import com.github.wirbelwind03.autosinistre.exception.NotFoundException;
import com.github.wirbelwind03.autosinistre.model.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ErrorResponseDTO.builder()
                .errorCode("VALIDATION_BAD_REQUEST")
                .message(errors)
                .build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleAlreadyExistException(AlreadyExistException e) {
        return ErrorResponseDTO.builder()
                .errorCode("ALREADY_EXIST_CONFLICT")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(NotFoundException e) {
        return ErrorResponseDTO.builder()
                .errorCode("NOT_FOUND")
                .message(e.getMessage())
                .build();
    }

}
