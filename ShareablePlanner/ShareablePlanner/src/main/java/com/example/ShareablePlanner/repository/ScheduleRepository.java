package com.example.ShareablePlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}