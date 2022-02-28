package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.dto.error.ErrorMessage;

@RestControllerAdvice
public class CrmErrorHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleIllegalArgumentException(
			IllegalArgumentException e) {
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public List<FieldErrorMessage> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		return e.getAllErrors().stream().;
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public List<FieldErrorMessage> handleConstraintViolationException(
			ConstraintViolationException e) {
		return new ErrorMessage(e.getMessage());
	}
	
}

record FieldErrorMessage(String field, String message) {}