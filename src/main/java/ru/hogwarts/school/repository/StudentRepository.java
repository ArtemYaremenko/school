package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    List<Student> findByFaculty_id(Long id);
}
