package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty newFaculty) {
        logger.debug("addFaculty - {}", newFaculty);
        return facultyRepository.save(newFaculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        logger.debug("findFaculty - {}", faculty);
        return faculty;
    }

    @Override
    public Faculty changeFaculty(Long id, Faculty modifiedFaculty) {
        modifiedFaculty.setId(id);
        logger.debug("changeFaculty - {}", modifiedFaculty);
        return facultyRepository.save(modifiedFaculty);
    }

    @Override
    public void removeFaculty(Long id) {
        logger.debug("removeFaculty id - {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getAllFaculty() {
        List<Faculty> faculties = facultyRepository.findAll();
        logger.debug("getAllFaculty - {}", faculties);
        return faculties;
    }

    @Override
    public List<Faculty> getFacultiesByNameOrColor(String nameOrColor) {
        List<Faculty> faculties = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
        logger.debug("getFacultiesByNameOrColor - {}", faculties);
        return faculties;
    }

    @Override
    public Faculty getStudentsFacultyByStudentId(Long id) {
        Faculty faculty = facultyRepository.findByStudentsId(id);
        logger.debug("getStudentsFacultyByStudentId - {}", faculty);
        return faculty;
    }
}
