package com.github.wirbelwind03.autosinistre.exception;

public class BadCredentialsException  extends RuntimeException {
    public BadCredentialsException(){
        super("Mot de passe incorrect");
    }
}
