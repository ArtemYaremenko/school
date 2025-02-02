package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student newStudent);

    Student findStudent(Long id);

    Student correctStudent(Long id, Student modifiedStudent);

    void removeStudent(Long id);

    List<Student> getAllStudents();

    List<Student> getStudentsByAge(Integer age);
}
