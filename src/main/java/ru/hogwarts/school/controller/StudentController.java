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
    public Student student(@RequestBody Student newStudent) {
        return service.addStudent(newStudent);
    }

    @GetMapping("/find/{id}")
    public Student studentById(@PathVariable("id") Long id) {
        return service.findStudent(id);
    }

    @PutMapping("/correct/{id}")
    public Student student(@PathVariable("id") Long id, @RequestBody Student modifiedStudent) {
        return service.correctStudent(id, modifiedStudent);
    }

    @DeleteMapping("/remove/{id}")
    public void student(@PathVariable("id") Long id) {
        service.removeStudent(id);
    }

    @GetMapping("/all")
    public List<Student> students() {
        return service.getAllStudents();
    }

    @GetMapping("/age/{age}")
    public List<Student> studentsByAge(@PathVariable("age") Integer age) {
        return service.getStudentsByAge(age);
    }

    @GetMapping("/age")
    public List<Student> studentsByAgeBetween(@RequestParam("min") Integer minAge, @RequestParam("max") Integer maxAge) {
        return service.getStudentsByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/faculty")
    public List<Student> studentsByFaculty(@RequestParam("id") Long id) {
        return service.getStudentsOfFacultyByFacultyId(id);
    }

}
