package ru.hogwarts.school.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.model.Student;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private StudentController studentController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student("Сара", 33);

        ResponseEntity<Student> entity = template.postForEntity("http://localhost:" + port + "/student/add", student, Student.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Student created = entity.getBody();

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Сара");
        assertThat(created.getAge()).isEqualTo(33);

        template.delete("http://localhost:" + port + "/student/remove/" + created.getId());
    }

    @Test
    public void testFindStudentById() throws Exception {
        Student found = template.getForObject("http://localhost:" + port + "/student/find/" + 1, Student.class);

        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getName()).isEqualTo("Harry");
        assertThat(found.getAge()).isEqualTo(11);
        assertThat(found.getFaculty().getId()).isEqualTo(1);
        assertThat(found.getFaculty().getName()).isEqualTo("Гриффиндор");
        assertThat(found.getFaculty().getColor()).isEqualTo("Красный");
    }

    @Test
    public void testRemoveStudent() throws Exception {
        Student student = new Student("Delete", 1);

        Student deletedStudent = template.postForObject("http://localhost:" + port + "/student/add", student, Student.class);
        template.delete("http://localhost:" + port + "/student/remove/" + deletedStudent.getId());
        ResponseEntity<Student> entity = template.getForEntity("http://localhost:" + port + "/student/find/" + deletedStudent.getId(), Student.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCorrectStudent() throws Exception {
        Student student1 = new Student("Original", 10);
        Student student2 = new Student("Changed", 20);

        HttpEntity<Student> entity = new HttpEntity<>(student2);

        Student original = template.postForObject("http://localhost:" + port + "/student/add", student1, Student.class);
        Student changed = template.exchange("http://localhost:" + port + "/student/correct/" + original.getId(), HttpMethod.PUT, entity, Student.class).getBody();

        assertThat(changed).isNotNull();
        assertThat(changed.getId()).isEqualTo(original.getId());
        assertThat(changed.getName()).isEqualTo("Changed");
        assertThat(changed.getAge()).isEqualTo(20);

        template.delete("http://localhost:" + port + "/student/remove/" + changed.getId());
    }

    @Test
    public void testAllStudents() throws Exception {
        Student student1 = new Student("One", 10);
        Student student2 = new Student("Two", 20);

        Student created1 = template.postForObject("http://localhost:" + port + "/student/add", student1, Student.class);
        Student created2 = template.postForObject("http://localhost:" + port + "/student/add", student2, Student.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Student>> entity = template.exchange("http://localhost:" + port + "/student/all", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Student>>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        List<Student> students = entity.getBody();

        assertThatIterable(students).contains(created1, created2);

        template.delete("http://localhost:" + port + "/student/remove/" + created1.getId());
        template.delete("http://localhost:" + port + "/student/remove/" + created2.getId());
    }

    @Test
    public void testStudentByAge() throws Exception {
        Student student1 = new Student("One", 10);
        Student student2 = new Student("Two", 10);

        Student created1 = template.postForObject("http://localhost:" + port + "/student/add", student1, Student.class);
        Student created2 = template.postForObject("http://localhost:" + port + "/student/add", student2, Student.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Student>> entity = template.exchange("http://localhost:" + port + "/student/age/" + 10, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Student>>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        List<Student> students = entity.getBody();

        assertThatIterable(students).contains(created1, created2);

        template.delete("http://localhost:" + port + "/student/remove/" + created1.getId());
        template.delete("http://localhost:" + port + "/student/remove/" + created2.getId());
    }

    @Test
    public void testStudentByAgeBetween() throws Exception {
        Student student1 = new Student("One", 10);
        Student student2 = new Student("Two", 10);

        Student created1 = template.postForObject("http://localhost:" + port + "/student/add", student1, Student.class);
        Student created2 = template.postForObject("http://localhost:" + port + "/student/add", student2, Student.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Student>> entity = template.exchange("http://localhost:" + port + "/student/age?min=" + 7 + "&max=" + 12, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Student>>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        List<Student> students = entity.getBody();

        assertThatIterable(students).contains(created1, created2);

        template.delete("http://localhost:" + port + "/student/remove/" + created1.getId());
        template.delete("http://localhost:" + port + "/student/remove/" + created2.getId());
    }

    @Test
    public void testStudentsByFaculty() throws Exception {
        Student student1 = template.getForObject("http://localhost:" + port + "/student/find/" + 1, Student.class);
        Student student2 = template.getForObject("http://localhost:" + port + "/student/find/" + 3, Student.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<Student>> entity = template.exchange("http://localhost:" + port + "/student/faculty?id=" + 1, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Student>>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        List<Student> students = entity.getBody();
        assertThatIterable(students).contains(student1, student2);
    }
}
