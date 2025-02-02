package ru.hogwarts.school.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student correctStudent(Long id, Student modifiedStudent) {
        modifiedStudent.setId(id);
        return studentRepository.save(modifiedStudent);
    }

    @Override
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getStudentsByAgeBetween(Integer minAge, Integer maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<Student> getStudentsOfFacultyByFacultyId(Long id) {
        return studentRepository.findByFaculty_id(id);
    }


}
