package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.obj.attendance;

public interface AttendanceRepository extends JpaRepository<attendance, Integer> {
}
