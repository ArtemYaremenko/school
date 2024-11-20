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

    @PutMapping("/change")
    private Faculty changeFaculty(@RequestBody Faculty changedFaculty) {
        return service.changeFaculty(changedFaculty);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFaculty(@PathVariable("id") Long id) {
        service.removeFaculty(id);
    }

    @GetMapping("/{color}")
    public List<Faculty> facultiesByColor(@PathVariable String color) {
        return service.getFacultiesByColor(color);
    }
}
