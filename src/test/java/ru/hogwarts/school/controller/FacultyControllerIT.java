package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private FacultyController facultyController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(facultyController).isNotNull();
    }

    @Test
    public void testAddFaculty() throws Exception {
        Faculty faculty = new Faculty("Факультет", "Зеленый");

        ResponseEntity<Faculty> entity = template.postForEntity("http://localhost:" + port + "/faculty/add", faculty, Faculty.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty created = entity.getBody();

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Факультет");
        assertThat(created.getColor()).isEqualTo("Зеленый");

        template.delete("http://localhost:" + port + "/faculty/remove/" + created.getId());
    }

    @Test
    public void testFindFacultyById() throws Exception {
        Faculty found = template.getForObject("http://localhost:" + port + "/faculty/find/" + 1, Faculty.class);

        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getName()).isEqualTo("Гриффиндор");
        assertThat(found.getColor()).isEqualTo("Красный");
    }

    @Test
    public void testRemoveFaculty() throws Exception {
        Faculty student = new Faculty("Delete", "Del");

        Faculty deletedStudent = template.postForObject("http://localhost:" + port + "/faculty/add", student, Faculty.class);
        template.delete("http://localhost:" + port + "/faculty/remove/" + deletedStudent.getId());
        ResponseEntity<Faculty> entity = template.getForEntity("http://localhost:" + port + "/faculty/find/" + deletedStudent.getId(), Faculty.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testChangeFaculty() throws Exception {
        Faculty faculty1 = new Faculty("Original", "Black");
        Faculty faculty2 = new Faculty("Changed", "White");

        HttpEntity<Faculty> entity = new HttpEntity<>(faculty2);

        Faculty original = template.postForObject("http://localhost:" + port + "/faculty/add", faculty1, Faculty.class);
        Faculty changed = template.exchange("http://localhost:" + port + "/faculty/change/" + original.getId(), HttpMethod.PUT, entity, Faculty.class).getBody();

        assertThat(changed).isNotNull();
        assertThat(changed.getId()).isEqualTo(original.getId());
        assertThat(changed.getName()).isEqualTo("Changed");
        assertThat(changed.getColor()).isEqualTo("White");

        template.delete("http://localhost:" + port + "/faculty/remove/" + changed.getId());
    }

    @Test
    public void testAllFaculty() throws Exception {
        Faculty faculty1 = new Faculty("One", "Red");
        Faculty faculty2 = new Faculty("Two", "Blue");

        Faculty created1 = template.postForObject("http://localhost:" + port + "/faculty/add", faculty1, Faculty.class);
        Faculty created2 = template.postForObject("http://localhost:" + port + "/faculty/add", faculty2, Faculty.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Faculty>> entity = template.exchange("http://localhost:" + port + "/faculty/all", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Faculty>>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        List<Faculty> faculties = entity.getBody();

        assertThatIterable(faculties).contains(created1, created2);

        template.delete("http://localhost:" + port + "/faculty/remove/" + created1.getId());
        template.delete("http://localhost:" + port + "/faculty/remove/" + created2.getId());
    }

    @Test
    public void testFacultyByNameOrColor() throws Exception {
        Faculty faculty1 = new Faculty("Faculty1", "Black");
        Faculty faculty2 = new Faculty("Faculty2", "Black");
        Faculty created1 = template.postForObject("http://localhost:" + port + "/faculty/add", faculty1, Faculty.class);
        Faculty created2 = template.postForObject("http://localhost:" + port + "/faculty/add", faculty2, Faculty.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        

        ResponseEntity<List<Faculty>> entityFound = template.exchange("http://localhost:" + port + "/faculty/find?nameOrColor=" +faculty1.getColor(), HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Faculty>>() {});

        List<Faculty> found = entityFound.getBody();

        assertThatIterable(found).contains(created1, created2);

        template.delete("http://localhost:" + port + "/faculty/remove/" + created1.getId());
        template.delete("http://localhost:" + port + "/faculty/remove/" + created2.getId());
    }

    @Test
    public void testStudentsFaculty() throws Exception {

        Faculty found = template.getForObject("http://localhost:" + port + "/faculty/student?id=" + 1, Faculty.class);

        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getName()).isEqualTo("Гриффиндор");
        assertThat(found.getColor()).isEqualTo("Красный");
    }

}
