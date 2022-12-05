package com.example.ShareablePlanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShareablePlanner.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	List<Person> findByUserName(String userName);
	List<Person> findByPassword(String password);
}
