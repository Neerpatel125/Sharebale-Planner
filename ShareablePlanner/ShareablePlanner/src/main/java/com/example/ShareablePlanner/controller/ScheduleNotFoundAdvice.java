package com.example.ShareablePlanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.ShareablePlanner.exception.ScheduleNotFoundException;

@ControllerAdvice
public class ScheduleNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(ScheduleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ScheduleNotFoundHandler(ScheduleNotFoundException ex) {
	  return ex.getMessage();
	}
}