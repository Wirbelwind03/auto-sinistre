package com.github.wirbelwind03.autosinistre.util;

import java.util.regex.Pattern;

public final class ValidationUtils {
    private ValidationUtils() {}

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValidEmail(String email){
        return Pattern.compile(EMAIL_REGEX)
                .matcher(email)
                .matches();
    }
}
