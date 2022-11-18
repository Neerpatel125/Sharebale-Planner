package com.example.ShareablePlanner.exception;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(long id) {
		super("Could not find person " + id);
	}
}

