package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student newStudent) {
        return service.addStudent(newStudent);
    }

    @GetMapping("/find/{id}")
    public Student findStudent(@PathVariable("id") Long id) {
        return service.findStudent(id);
    }

    @PutMapping("/correct/{id}")
    public Student correctStudent(@PathVariable("id") Long id, @RequestBody Student modifiedStudent) {
        return service.correctStudent(id, modifiedStudent);
    }

    @DeleteMapping("/remove/{id}")
    public Student removeStudent(@PathVariable Long id) {
        return service.removeStudent(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> studentsByAge(@PathVariable Integer age) {
        return service.getStudentsByAge(age);
    }
}
