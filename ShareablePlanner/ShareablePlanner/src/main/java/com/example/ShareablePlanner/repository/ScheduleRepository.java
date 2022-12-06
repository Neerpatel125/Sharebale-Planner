package com.example.ShareablePlanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Person;
import com.example.ShareablePlanner.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	List<Schedule> findByDateAndPersonId(String date, Person personId);
	Schedule findByPersonIdAndDateAndTimeAndName(Person personId, String date, String time, String name);
}