package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyFacultyException extends RuntimeException {
    public EmptyFacultyException() {
    }

    public EmptyFacultyException(String message) {
        super(message);
    }
}
