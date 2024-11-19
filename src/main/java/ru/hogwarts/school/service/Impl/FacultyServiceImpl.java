package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {

    private Long lastId = 0L;

    private Map<Long, Faculty> faculties;

    public FacultyServiceImpl() {
        this.faculties = new HashMap<>();
    }

    @Override
    public Faculty addFaculty(Faculty newFaculty) {
        newFaculty.setId(++lastId);
        faculties.put(lastId, newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty changeFaculty(Long id, Faculty modifiedFaculty) {
        modifiedFaculty.setId(id);
        faculties.put(id, modifiedFaculty);
        return modifiedFaculty;
    }

    @Override
    public Faculty removeFaculty(Long id) {
        return faculties.remove(id);
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }
}
