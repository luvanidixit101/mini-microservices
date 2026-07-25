package com.campus.student_service;

// A "record" is a short way to make a class that only carries data.
public record Student(Long id, String name, String email) {

}