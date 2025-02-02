package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty newFaculty);

    Faculty findFaculty(Long id);

    Faculty changeFaculty(Long id, Faculty modifiedFaculty);

    void removeFaculty(Long id);

    List<Faculty> getAllFaculty();

    List<Faculty> getFacultiesByNameOrColor(String nameOrColor);

    Faculty getStudentsFacultyByStudentId(Long id);

}
