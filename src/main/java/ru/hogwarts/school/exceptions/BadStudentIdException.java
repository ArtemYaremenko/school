package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadStudentIdException extends RuntimeException {

    public BadStudentIdException() {
    }

    public BadStudentIdException(String message) {
        super(message);
    }
}
