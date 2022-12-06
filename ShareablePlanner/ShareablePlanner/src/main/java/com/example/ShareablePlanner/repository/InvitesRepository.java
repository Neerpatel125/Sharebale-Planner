package com.example.ShareablePlanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Invites;
import com.example.ShareablePlanner.domain.Person;
import com.example.ShareablePlanner.domain.Schedule;

public interface InvitesRepository extends JpaRepository<Invites, Long>{
	List<Invites> findByInvitee(Person inviteeId);
	List<Invites> findBySchedule(Schedule scheduleId);
}