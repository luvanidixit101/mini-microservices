package com.campus.course_service;

// DTO = Data Transfer Object: the shape of data coming from student-service.
// Field names MUST match student-service's Student record (id, name, email).
public record StudentDto(Long id, String name, String email) {
}