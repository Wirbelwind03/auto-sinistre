package com.github.wirbelwind03.autosinistre.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Aucun compte associé à cet email");
    }
}
