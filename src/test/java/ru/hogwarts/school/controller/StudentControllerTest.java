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
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.Impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentServiceImpl studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testAddStudent() throws Exception {
        Long id = 1L;
        String name = "Harry";
        Integer age = 11;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student(name, age);
        student.setId(id);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/student/add")
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testFindStudent() throws Exception {
        Long id = 1L;
        String name = "Harry";
        Integer age = 11;

        Student student = new Student(name, age);
        student.setId(id);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testFindStudentWhenStudentNotExist() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/find/9"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCorrectStudent() throws Exception {
        Long id = 1L;
        String name = "Harry";
        Integer age = 11;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student(name, age);
        student.setId(id);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/correct/" + id)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testRemoveStudent() throws Exception {
        long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/remove/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testAllStudents() throws Exception {
        List<Student> students = List.of(
                new Student("Student1", 1),
                new Student("Student2", 2)
        );

        when(studentRepository.findAll()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void testFindStudentByAge() throws Exception {
        String name = "Harry";
        Integer age = 11;

        Student student = new Student(name, age);
        List<Student> students = List.of(student);

        when(studentRepository.findByAge(any(Integer.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/" + age))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testFindStudentByAgeBetween() throws Exception {
        String name = "Harry";
        Integer age = 11;

        Student student = new Student(name, age);
        List<Student> students = List.of(student);

        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age?min=10&max=12" + age))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testFindStudentByFaculty() throws Exception {
        String studentName = "Harry";
        Integer age = 11;
        Student student = new Student(studentName, age);

        Long facultyId = 1L;

        List<Student> students = List.of(student);

        when(studentRepository.findByFaculty_id(any(Long.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty?id=" + facultyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
