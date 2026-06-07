package com.github.wirbelwind03.autosinistre.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(){
        super("L'émail n'est pas valide.");
    }
}
