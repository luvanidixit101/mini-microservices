package com.campus.student_service;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // "this class answers HTTP requests with JSON"
@RequestMapping("/students") // every URL here starts with /students
public class StudentController {

    // To stay simple we keep data in a list instead of a database.
    private final List<Student> students = List.of(
            new Student(1L, "Aarav Shah", "aarav@campus.edu"),
            new Student(2L, "Priya Nair", "priya@campus.edu"),
            new Student(3L, "Rahul Verma", "rahul@campus.edu"));

    // GET http://localhost:8081/students -> all students
    @GetMapping
    public List<Student> all() {
        return students;
    }

    // GET http://localhost:8081/students/2 -> one student
    @GetMapping("/{id}")
    public Student byId(@PathVariable Long id) {
        return students.stream()
                .filter(s -> s.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }
}