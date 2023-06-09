package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.obj.student;

public interface StudentRepository extends JpaRepository<student, Integer> {
}
