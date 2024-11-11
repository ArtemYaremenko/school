package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.lang.module.ResolvedModule;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent) {
        return ResponseEntity.ok(service.addStudent(newStudent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.findStudent(id));
    }

    @PutMapping
    public ResponseEntity<Student> correctStudent(@RequestBody Student modifiedStudent) {
        return ResponseEntity.ok(service.correctStudent(modifiedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.removeStudent(id));
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> studentsByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(service.getStudentsByAge(age));
    }
}
