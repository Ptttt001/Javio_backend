package com.example.demo.controller;
import com.example.demo.repository.CourseRepository;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/cou")
public class CourseController {
  private final CourseRepository courseRepository;
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
      }
    }
