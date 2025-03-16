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

    @GetMapping("/print-parallel")
    public void printParallel() {
        List<Student> students = service.getAllStudents();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() ->    {
                System.out.println(students.get(2));
                System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
    }

    @GetMapping("/print-synchronized")
    public void printSynchronized() {
        int studentNumber = 0;
        List<Student> students = service.getAllStudents();
        printStudent();
        printStudent();

        new Thread(() -> {
            printStudent();
            printStudent();
        }).start();

        new Thread(() -> {
            printStudent();
            printStudent();
        }).start();
    }
    public synchronized void printStudent() {
        List<Student> students = service.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }


}
