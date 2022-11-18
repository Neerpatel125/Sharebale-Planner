package com.example.ShareablePlanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.ShareablePlanner.exception.StatusNotFoundException;

@ControllerAdvice
public class StatusNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(StatusNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String AvailabilityNotFoundHandler(StatusNotFoundException ex) {
	  return ex.getMessage();
	}
}