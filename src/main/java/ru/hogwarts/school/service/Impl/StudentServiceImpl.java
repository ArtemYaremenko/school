package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.AmountOfStudents;
import ru.hogwarts.school.model.AverageAge;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student newStudent) {
        logger.debug("AddStudent - {}", newStudent);
        return studentRepository.save(newStudent);
    }

    @Override
    public Student findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        logger.debug("findStudent - {}", student);
        return student;
    }

    @Override
    public Student correctStudent(Long id, Student modifiedStudent) {
        modifiedStudent.setId(id);
        logger.debug("correctStudent - {}", modifiedStudent);
        return studentRepository.save(modifiedStudent);
    }

    @Override
    public void removeStudent(Long id) {
        logger.debug("removeStudent id - {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        logger.debug("findAll - {}", students);
        return students;
    }

    @Override
    public List<Student> getStudentsByAge(Integer age) {
        List<Student> students = studentRepository.findByAge(age);
        logger.debug("getStudentsByAge - {}", students);
        return students;
    }

    @Override
    public List<Student> getStudentsByAgeBetween(Integer minAge, Integer maxAge) {
        List<Student> students = studentRepository.findByAgeBetween(minAge, maxAge);
        logger.debug("getStudentsByAgeBetween - {}", students);
        return students;
    }

    @Override
    public List<Student> getStudentsOfFacultyByFacultyId(Long id) {
        List<Student> students = studentRepository.findByFaculty_id(id);
        logger.debug("getStudentsOfFacultyByFacultyId - {}", students);
        return students;
    }

    @Override
    public AmountOfStudents calculateAllStudents() {
        AmountOfStudents amount = studentRepository.getAmountOfStudents();
        logger.debug("AmountOfStudents - {}", amount.getAmountOfStudents());
        return amount;
    }

    @Override
    public AverageAge calculateAverageAgeOfAllStudents() {
        AverageAge average = studentRepository.getAverageAge();
        logger.debug("AverageAge - {}", average.getAverageAge());
        return average;
    }

    @Override
    public List<Student> findLastStudents(Integer number) {
        List<Student> students = studentRepository.findLastStudents(number);
        logger.debug("findLastStudents - {}", students);
        return students;
    }
}
