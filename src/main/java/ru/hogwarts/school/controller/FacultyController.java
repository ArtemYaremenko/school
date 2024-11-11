package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty newFaculty) {
        return ResponseEntity.ok(service.addFaculty(newFaculty));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(service.findFaculty(id));
    }

    @PutMapping
    private ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty changedFaculty) {
        return ResponseEntity.ok(service.changeFaculty(changedFaculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(service.removeFaculty(id));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Faculty>> facultiesByColor(@PathVariable String color) {
        return ResponseEntity.ok(service.getFacultiesByColor(color));
    }
}
