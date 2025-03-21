package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FindFacultyControllerException extends RuntimeException{
    public FindFacultyControllerException() {
    }

    public FindFacultyControllerException(String message) {
        super(message);
    }
}
