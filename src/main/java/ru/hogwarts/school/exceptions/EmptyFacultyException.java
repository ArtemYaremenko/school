package ru.hogwarts.school.exceptions;

public class EmptyFacultyException extends RuntimeException {
    public EmptyFacultyException() {
    }

    public EmptyFacultyException(String message) {
        super(message);
    }
}
