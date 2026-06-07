package com.github.wirbelwind03.autosinistre.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(){
        super("Un compte existe déjà avec cette email.");
    }
}
