package com.example.crm.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.dto.error.ErrorMessage;

@RestControllerAdvice
public class CrmErrorHandler {


	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<FieldErrorMessage> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		return e.getAllErrors()
				.stream()
				.map(oe -> new FieldErrorMessage(oe.getObjectName(), oe.getDefaultMessage()))
				.toList();
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<FieldErrorMessage> handleConstraintViolationException(
			ConstraintViolationException e) {
		return e.getConstraintViolations()
				.stream()
				.map(cv -> new FieldErrorMessage(cv.getPropertyPath().toString(),cv.getMessage()))
				.toList();
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleAllOtherExceptions(Throwable t) {
		return new ErrorMessage(t.getMessage(),100,"");
	}
	
}

record FieldErrorMessage(String field, String message) {}