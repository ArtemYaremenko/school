package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyStudentException extends RuntimeException {
    public EmptyStudentException() {
    }

    public EmptyStudentException(String message) {
        super(message);
    }
}
