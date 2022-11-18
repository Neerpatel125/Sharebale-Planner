package com.example.ShareablePlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
