package com.example.ShareablePlanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Invites;
import com.example.ShareablePlanner.domain.Person;

public interface InvitesRepository extends JpaRepository<Invites, Long>{
	List<Invites> findByInvitee(Person inviteeId);
}