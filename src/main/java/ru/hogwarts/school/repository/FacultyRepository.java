package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

    @Query(value = "select f.id, f.name, f.color from student s, faculty f where s.faculty_id = f.id and s.id = :id", nativeQuery = true)
    Faculty findStudentsFaculty(@Param("id") Long id);
}
