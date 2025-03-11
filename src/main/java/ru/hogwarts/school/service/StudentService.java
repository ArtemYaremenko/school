package ru.hogwarts.school.service;

import ru.hogwarts.school.model.AmountOfStudents;
import ru.hogwarts.school.model.AverageAge;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student newStudent);

    Student findStudent(Long id);

    Student correctStudent(Long id, Student modifiedStudent);

    void removeStudent(Long id);

    List<Student> getAllStudents();

    List<Student> getStudentsByAge(Integer age);

    List<Student> getStudentsByAgeBetween(Integer minAge, Integer maxAge);

    List<Student> getStudentsOfFacultyByFacultyId(Long id);

    AmountOfStudents calculateAllStudents();

    AverageAge calculateAverageAgeOfAllStudents();

    List<Student> findLastStudents(Integer amount);

    Double getAverageAgeOfAllStudents();

    List<String> getNamesStarringWithA();

}
