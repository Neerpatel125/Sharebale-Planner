package com.example.ShareablePlanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.ShareablePlanner.exception.PersonNotFoundException;

@ControllerAdvice
public class PersonNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String AvailabilityNotFoundHandler(PersonNotFoundException ex) {
	  return ex.getMessage();
	}
}