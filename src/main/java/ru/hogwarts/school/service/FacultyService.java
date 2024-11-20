package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty addFaculty(Faculty newFaculty);

    Faculty findFaculty(Long id);

    Faculty changeFaculty(Faculty modifiedFaculty);

    void removeFaculty(Long id);

    List<Faculty> getFacultiesByColor(String color);
}
