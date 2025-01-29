package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Faculty not found!")
public class FacultyNotFoundException extends RuntimeException {
    public FacultyNotFoundException() {
    }

    public FacultyNotFoundException(Long id) {
        super("Faculty %s - not found!".formatted(id));
    }
}
