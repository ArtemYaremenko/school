package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student not found!")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(Long id) {
        super("Student %s - not found!".formatted(id));
    }
}
