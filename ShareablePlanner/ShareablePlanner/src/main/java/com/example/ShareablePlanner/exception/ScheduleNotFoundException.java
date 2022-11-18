package com.example.ShareablePlanner.exception;

public class ScheduleNotFoundException extends RuntimeException {
	public ScheduleNotFoundException(long id) {
		super("Unavailable schedule " + id);
	}
}