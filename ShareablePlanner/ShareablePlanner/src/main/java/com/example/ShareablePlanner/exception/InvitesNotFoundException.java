package com.example.ShareablePlanner.exception;

public class InvitesNotFoundException extends RuntimeException {
	public InvitesNotFoundException(long id) {
		super("Could not find Invitation " + id);
	}
}

