package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.BadStudentIdException;
import ru.hogwarts.school.exceptions.EmptyStudentException;
import ru.hogwarts.school.exceptions.NotFoundStudentException;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private Long lastId = 0L;

    private Map<Long, Student> students;

    public StudentService() {
        this.students = new HashMap<>();
    }

    public Student addStudent(Student newStudent) {
        if (newStudent.isEmpty()) {
            throw new EmptyStudentException("Не все поля заполнены!");
        }
        newStudent.setId(++lastId);
        students.put(lastId, newStudent);
        return newStudent;
    }

    public Student findStudent(Long id) {
        if (id <= 0) {
            throw new BadStudentIdException("Некорректный id=" + id);
        }
        if (!students.containsKey(id)) {
            throw new NotFoundStudentException("Не найден студент с id=" + id);
        }
        return students.get(id);
    }

    public Student correctStudent(Student modifiedStudent) {
        if (modifiedStudent.isEmpty()) {
            throw new EmptyStudentException("Не все поля заполнены!");
        }
        if (!students.containsValue(modifiedStudent)) {
            throw new NotFoundStudentException("Студент - " + modifiedStudent.toString() + " не найден!");
        }
        students.put(modifiedStudent.getId(), modifiedStudent);
        return modifiedStudent;
    }

    public Student removeStudent(Long id) {
        if (id <= 0) {
            throw new BadStudentIdException("Некорректный id=" + id);
        }
        if (!students.containsKey(id)) {
            throw new NotFoundStudentException("Не найден студент с id=" + id);
        }
        return students.remove(id);
    }

    public Long getLastId() {
        return lastId;
    }
}
