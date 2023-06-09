package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.obj.course;

public interface CourseRepository extends JpaRepository<course, Integer> {
}
