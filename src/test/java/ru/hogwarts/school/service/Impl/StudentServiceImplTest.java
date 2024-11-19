package ru.hogwarts.school.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.constants.StudentsTest.*;
class StudentServiceImplTest {

    private final StudentServiceImpl out = new StudentServiceImpl();

    @ParameterizedTest
    @MethodSource("validStudentsForTest")
    void addStudent(Student expected) {
        //test
        Student actual = out.addStudent(expected);
        Long testId = actual.getId();

        //check
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAge(), actual.getAge());
        assertNotNull(actual.getId());
        assertNotNull(out.findStudent(testId));
    }

    @Test
    void findStudent() {
        Student expected = out.addStudent(HarryPotter);
        Long testID = expected.getId();

        //test
        Student actual = out.findStudent(testID);

        //check
        assertEquals(expected, actual);
    }

    @Test
    void correctStudent() {
        Student student = out.addStudent(HarryPotter);
        Student expected = RonWeasley;

        //test
        Student actual = out.correctStudent(student.getId(), expected);

        //check
        assertEquals(expected, actual);
        assertSame(expected.getId(), actual.getId());
    }

    @Test
    void removeStudent() {
        Student expected = out.addStudent(HermioneGranger);
        Long testId = expected.getId();

        //test
        Student actual = out.removeStudent(testId);

        //check
        assertEquals(expected, actual);
        assertNull(out.findStudent(testId));
    }

    @Test
    void getStudentsByAge() {
        out.addStudent(HarryPotter);
        out.addStudent(RonWeasley);
        out.addStudent(HermioneGranger);
        Integer testAge = 11;
        List<Student> expected = List.of(HarryPotter, RonWeasley);

        //test
        List<Student> actual = out.getStudentsByAge(testAge);

        //check
        assertIterableEquals(expected, actual);
    }

    private static Stream<Arguments> validStudentsForTest() {
        return Stream.of(Arguments.of(HarryPotter),
                Arguments.of(RonWeasley),
                Arguments.of(HermioneGranger));
    }
}