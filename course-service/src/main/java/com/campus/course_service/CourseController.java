package com.campus.course_service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final List<Course> courses = List.of(
            new Course(1L, "Microservices with Spring", 30),
            new Course(2L, "Cloud Computing", 40),
            new Course(3L, "Data Structures", 60));

    // Dependencies
    private final StudentClient studentClient;
    private final EnrollmentPublisher publisher;

    // Constructor Injection
    public CourseController(StudentClient studentClient,
            EnrollmentPublisher publisher) {
        this.studentClient = studentClient;
        this.publisher = publisher;
    }

    // GET http://localhost:8082/courses
    @GetMapping
    public List<Course> all() {
        return courses;
    }

    // POST http://localhost:8082/courses/{courseId}/enroll/{studentId}
    @PostMapping("/{courseId}/enroll/{studentId}")
    public Map<String, Object> enroll(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {

        Course course = courses.stream()
                .filter(c -> c.id().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        // Call Student Service
        StudentDto student = studentClient.getStudent(studentId);

        // Publish enrollment event
        publisher.publish(
                new EnrollmentEvent(
                        courseId,
                        studentId,
                        student.name()));

        return Map.of(
                "message", "Enrolment successful!",
                "course", course.title(),
                "student", student.name(),
                "email", student.email());
    }
}