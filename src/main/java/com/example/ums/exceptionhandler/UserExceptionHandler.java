package com.example.ums.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ums.exception.UserNotFoundByIdException;
import com.example.ums.util.AppResponseBuilder;
import com.example.ums.util.ErrorStructure;

@RestControllerAdvice
public class UserExceptionHandler {
	
	private AppResponseBuilder responseBuilder;
	
	
	public UserExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}


	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handleUserNotFoundException(UserNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "user not found by id");
	}
}
