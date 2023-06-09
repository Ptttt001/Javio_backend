package com.example.demo.controller;

import com.example.demo.repository.AttendanceRepository;
import javax.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;

@RestController
// @RequestMapping("/")
public class AttendanceController {

  @PersistenceContext
  private EntityManager entityManager;

  private final AttendanceRepository attendanceRepository;

  public AttendanceController(AttendanceRepository attendanceRepository) {
    this.attendanceRepository = attendanceRepository;
  }

  
}
