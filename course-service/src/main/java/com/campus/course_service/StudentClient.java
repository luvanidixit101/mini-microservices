package com.campus.course_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = the OTHER service's spring.application.name in Eureka.
// Notice: NO URL, NO IP, NO PORT anywhere!
@FeignClient(name = "student-service", fallback = StudentClientFallback.class)
public interface StudentClient {

    // Must match the endpoint that exists in student-service: GET /students/{id}
    @GetMapping("/students/{id}")
    StudentDto getStudent(@PathVariable("id") Long id);
}