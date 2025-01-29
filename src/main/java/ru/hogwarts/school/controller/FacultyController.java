package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty newFaculty) {
        return service.addFaculty(newFaculty);
    }

    @GetMapping("/find/{id}")
    public Faculty findFaculty(@PathVariable("id") Long id) {
        return service.findFaculty(id);
    }

    @PutMapping("/change/{id}")
    private Faculty changeFaculty(@PathVariable("id") Long id, @RequestBody Faculty changedFaculty) {
        return service.changeFaculty(id, changedFaculty);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFaculty(@PathVariable("id") Long id) {
        service.removeFaculty(id);
    }

    @GetMapping("/all")
    public List<Faculty> allFaculties() {
        return service.getAllFaculty();
    }

    @GetMapping("/{color}")
    public List<Faculty> facultiesByColor(@PathVariable String color) {
        return service.getFacultiesByColor(color);
    }

    @GetMapping("student/{id}")
    public Faculty studentsFaculty(@PathVariable("id") Long id) {
        return service.getStudentsFacultyByStudentId(id);
    }
}
