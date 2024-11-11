package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAgeException extends RuntimeException {

    public BadAgeException() {
    }

    public BadAgeException(String message) {
        super(message);
    }
}
