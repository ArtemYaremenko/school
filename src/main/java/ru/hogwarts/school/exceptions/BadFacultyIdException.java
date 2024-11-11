package ru.hogwarts.school.exceptions;

public class BadFacultyIdException extends RuntimeException {
    public BadFacultyIdException() {
    }

    public BadFacultyIdException(String message) {
        super(message);
    }
}
