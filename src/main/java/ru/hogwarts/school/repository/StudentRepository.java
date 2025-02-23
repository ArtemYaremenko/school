package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.AmountOfStudents;
import ru.hogwarts.school.model.AverageAge;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    List<Student> findByFaculty_id(Long id);

    @Query(value = "SELECT COUNT(*) AS amountOfStudents FROM STUDENT", nativeQuery = true)
    AmountOfStudents getAllStudents();

    @Query(value = "SELECT AVG(age) AS averageAge FROM STUDENT", nativeQuery = true)
    AverageAge getAverageAge();

    @Query(value = "SELECT * FROM STUDENT ORDER BY id DESC LIMIT :number", nativeQuery = true)
    List<Student> findLastStudents(Integer number);
}
