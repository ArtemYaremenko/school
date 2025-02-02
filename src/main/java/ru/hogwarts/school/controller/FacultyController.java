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
    public Faculty faculty(@RequestBody Faculty newFaculty) {
        return service.addFaculty(newFaculty);
    }

    @GetMapping("/find/{id}")
    public Faculty facultyById(@PathVariable("id") Long id) {
        return service.findFaculty(id);
    }

    @PutMapping("/change/{id}")
    private Faculty faculty(@PathVariable("id") Long id, @RequestBody Faculty changedFaculty) {
        return service.changeFaculty(id, changedFaculty);
    }

    @DeleteMapping("/remove/{id}")
    public void faculty(@PathVariable("id") Long id) {
        service.removeFaculty(id);
    }

    @GetMapping("/all")
    public List<Faculty> faculties() {
        return service.getAllFaculty();
    }

    @GetMapping("/find/{name_or_color}")
    public List<Faculty> facultyByNameOrColor(@PathVariable String nameOrColor) {
        return service.getFacultiesByNameOrColor(nameOrColor);
    }

    @GetMapping("/student")
    public Faculty studentsFaculty(@RequestParam("id") Long id) {
        return service.getStudentsFacultyByStudentId(id);
    }




}
