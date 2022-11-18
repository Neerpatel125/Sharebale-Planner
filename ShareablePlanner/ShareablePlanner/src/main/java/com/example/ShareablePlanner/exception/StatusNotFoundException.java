package com.example.ShareablePlanner.exception;

public class StatusNotFoundException extends RuntimeException {
	public StatusNotFoundException(long id) {
		super("Could not find Status " + id);
	}
}

