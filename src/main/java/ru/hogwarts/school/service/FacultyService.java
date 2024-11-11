package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.*;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class FacultyService {

    private Long lastId = 0L;

    private Map<Long, Faculty> faculties;

    public FacultyService() {
        this.faculties = new HashMap<>();
    }

    public Faculty addFaculty(Faculty newFaculty) {
        if (newFaculty.isEmpty()) {
            throw new EmptyFacultyException("Не все поля заполнены!");
        }
        newFaculty.setId(++lastId);
        faculties.put(lastId, newFaculty);
        return newFaculty;
    }

    public Faculty findFaculty(Long id) {
        if (id <= 0) {
            throw new BadFacultyIdException("Некорректный id=" + id);
        }
        if (!faculties.containsKey(id)) {
            throw new NotFoundFacultyException("Не найден факультет с id=" + id);
        }
        return faculties.get(id);
    }

    public Faculty changeFaculty(Faculty modifiedFaculty) {
        if (modifiedFaculty.isEmpty()) {
            throw new EmptyFacultyException("Не все поля заполнены!");
        }
        if (!faculties.containsValue(modifiedFaculty)) {
            throw new NotFoundFacultyException("Факультет - " + modifiedFaculty.toString() + " не найден!");
        }
        faculties.put(modifiedFaculty.getId(), modifiedFaculty);
        return modifiedFaculty;
    }

    public Faculty removeFaculty(Long id) {
        if (id <= 0) {
            throw new BadFacultyIdException("Некорректный id=" + id);
        }
        if (!faculties.containsKey(id)) {
            throw new NotFoundFacultyException("Не найден факультет с id=" + id);
        }
        return faculties.remove(id);
    }

    public Long getLastId() {
        return lastId;
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> Objects.equals(faculty.getColor(), color))
                .toList();
    }
}
