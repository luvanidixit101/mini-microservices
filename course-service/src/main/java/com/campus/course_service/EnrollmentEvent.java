package com.campus.course_service;

import java.io.Serializable;

public record EnrollmentEvent(Long courseId, Long studentId, String studentName) implements Serializable {
}