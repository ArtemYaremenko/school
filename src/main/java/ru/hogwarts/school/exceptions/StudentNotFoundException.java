package ru.hogwarts.school.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student not found!")
public class StudentNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(StudentNotFoundException.class);
    public StudentNotFoundException() {
        logger.warn("StudentNotFoundException");
    }

    public StudentNotFoundException(Long id) {
        super("Student %s - not found!".formatted(id));
    }
}
