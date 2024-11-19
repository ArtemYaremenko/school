package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Long lastId = 0L;

    private Map<Long, Student> students;

    public StudentServiceImpl() {
        this.students = new HashMap<>();
    }

    @Override
    public Student addStudent(Student newStudent) {
        newStudent.setId(++lastId);
        students.put(newStudent.getId(), newStudent);
        return newStudent;
    }

    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student correctStudent(Long id, Student modifiedStudent) {
        modifiedStudent.setId(id);
        students.put(id, modifiedStudent);
        return modifiedStudent;
    }

    @Override
    public Student removeStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public List<Student> getStudentsByAge(Integer age) {
        return students.values().stream()
                .filter(student -> student.getAge().equals(age))
                .toList();
    }
}
