package ru.hogwarts.school.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Faculty not found!")
public class FacultyNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(FacultyNotFoundException.class);
    public FacultyNotFoundException() {
        logger.warn("FacultyNotFoundException");
    }

    public FacultyNotFoundException(Long id) {
        super("Faculty %s - not found!".formatted(id));
    }
}
