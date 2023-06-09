package com.example.demo.controller;
import com.example.demo.repository.ProfessorRepository;

//import java.util.Optional;
//import org.springframework.http.ResponseEntity;
//import org.apache.catalina.webresources.Cache;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/pro")
public class ProfessorController {
  private final ProfessorRepository professorRepository;
    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
      }
        

    }

    