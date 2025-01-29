package ru.hogwarts.school.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.constants.FacultiesTest.*;

class FacultyServiceImplTest {

//    private final FacultyServiceImpl out = new FacultyServiceImpl();
//
//    @ParameterizedTest
//    @MethodSource("validFacultiesForTest")
//    void addFaculty(Faculty expected) {
//        //test
//        Faculty actual = out.addFaculty(expected);
//        Long testId = actual.getId();
//
//        //check
//        assertEquals(expected.getName(), actual.getName());
//        assertEquals(expected.getColor(), actual.getColor());
//        assertNotNull(actual.getId());
//        assertNotNull(out.findFaculty(testId));
//    }
//
//    @Test
//    void findFaculty() {
//        Faculty expected = out.addFaculty(Gryffindor);
//        Long testId = expected.getId();
//
//        //test
//        Faculty actual = out.findFaculty(testId);
//
//        //check
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void changeFaculty() {
//        Faculty faculty = out.addFaculty(Ravenclaw);
//        Faculty expected = Slytherin;
//
//        //test
//        Faculty actual = out.changeFaculty(faculty.getId(), expected);
//
//        //check
//        assertEquals(expected, actual);
//        assertSame(expected.getId(), actual.getId());
//    }
//
//    @Test
//    void removeFaculty() {
//        Faculty expected = out.addFaculty(Hufflepuff);
//        Long testId = expected.getId();
//
//        //test
//        Faculty actual = out.removeFaculty(testId);
//
//        //check
//        assertEquals(expected, actual);
//        assertNull(out.findFaculty(testId));
//    }
//
//    @Test
//    void getFacultiesByColor() {
//        out.addFaculty(Gryffindor);
//        out.addFaculty(Ravenclaw);
//        out.addFaculty(Hufflepuff);
//        out.addFaculty(Slytherin);
//        String testColor = "Red";
//        List<Faculty> expected = List.of(Gryffindor);
//
//        //test
//        List<Faculty> actual = out.getFacultiesByColor(testColor);
//
//        //check
//        assertIterableEquals(expected, actual);
//    }
//
//    private static Stream<Arguments> validFacultiesForTest() {
//        return Stream.of(Arguments.of(Gryffindor),
//                Arguments.of(Ravenclaw),
//                Arguments.of(Hufflepuff),
//                Arguments.of(Slytherin));
//    }
}