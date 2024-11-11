package ru.hogwarts.school.exceptions;

public class NotFoundFacultyException extends RuntimeException {
    public NotFoundFacultyException() {
    }

    public NotFoundFacultyException(String message) {
        super(message);
    }
}
