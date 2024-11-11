package ru.hogwarts.school.exceptions;

public class BadStudentIdException extends RuntimeException {

    public BadStudentIdException() {
    }

    public BadStudentIdException(String message) {
        super(message);
    }
}
