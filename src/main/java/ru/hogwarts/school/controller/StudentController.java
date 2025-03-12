package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.AmountOfStudents;
import ru.hogwarts.school.model.AverageAge;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student newStudent) {
        return service.addStudent(newStudent);
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Long id) {
        return service.findStudent(id);
    }

    @PutMapping("/{id}")
    public Student correctStudent(@PathVariable("id") Long id, @RequestBody Student modifiedStudent) {
        return service.correctStudent(id, modifiedStudent);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable("id") Long id) {
        service.removeStudent(id);
    }

    @GetMapping("/all")
    public List<Student> findAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/age/{age}")
    public List<Student> findStudentsByAge(@PathVariable("age") Integer age) {
        return service.getStudentsByAge(age);
    }

    @GetMapping("/age")
    public List<Student> findStudentsByAgeBetween(@RequestParam("min") Integer minAge, @RequestParam("max") Integer maxAge) {
        return service.getStudentsByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/faculty")
    public List<Student> findStudentsByFaculty(@RequestParam("id") Long id) {
        return service.getStudentsOfFacultyByFacultyId(id);
    }

    @GetMapping("/amount")
    public AmountOfStudents calculateAllStudents() {
        return service.calculateAllStudents();
    }

    @GetMapping("/average")
    public AverageAge calculateAverageAgeOfAllStudents() {
        return service.calculateAverageAgeOfAllStudents();
    }

    @GetMapping("/last")
    public List<Student> findLastStudents(@RequestParam("amount") Integer amount) {
        return service.findLastStudents(amount);
    }

    @GetMapping("/average_age")
    public Double getAverageAgeOfAllStudents() {
        return service.getAverageAgeOfAllStudents();
    }

    @GetMapping("/name/A")
    public List<String> getNamesStarringWithA() {
        return service.getNamesStarringWithA();
    }

}
