package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.Impl.FacultyServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FacultyRepository facultyRepository;

    @SpyBean
    FacultyServiceImpl facultyService;

    @InjectMocks
    FacultyController facultyController;

    @Test
    public void testAddFaculty() throws Exception {
        Long id = 1L;
        String name = "Gryffindor";
        String color = "Red";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", color);

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty/add")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testFindFaculty() throws Exception {
        Long id = 1L;
        String name = "Gryffindor";
        String color = "Red";

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testFindFacultyWhenFacultyNotExist() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find/999999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testChangeFaculty() throws Exception {
        Long id = 1L;
        String name = "Gryffindor";
        String color = "Red";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", color);

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/change/" + id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testRemoveStudent() throws Exception {
        long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/remove/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testAllFaculties() throws Exception {
        List<Faculty> faculties = List.of(
                new Faculty("Gryffindor", "Red"),
                new Faculty("Slytherin", "Green")
        );

        when(facultyRepository.findAll()).thenReturn(faculties);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void testFindFacultyByNameOrColor() throws Exception {
        String name = "Gryffindor";
        String color = "Red";

        Faculty faculty = new Faculty(name, color);
        List<Faculty> faculties = List.of(faculty);

        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(any(String.class), any(String.class))).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find?nameOrColor=" + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testFindStudentsFaculty() throws Exception {
        Long id = 1l;
        String name = "Gryffindor";
        String color = "Red";

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(facultyRepository.findByStudentsId(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/student?id=" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
}
