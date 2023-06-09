package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.obj.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
