package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student newStudent);

    Student findStudent(Long id);

    Student correctStudent(Long id, Student modifiedStudent);

    Student removeStudent(Long id);

    List<Student> getStudentsByAge(Integer age);
}
