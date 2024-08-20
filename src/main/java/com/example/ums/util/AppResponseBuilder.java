package com.example.ums.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class AppResponseBuilder {

	public<T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status,String message,T data){
		return ResponseEntity.status(status).body(ResponseStructure.create(status.value(), message, data));
	}
	public ResponseEntity<ErrorStructure> error(HttpStatus status,String message,String rootCause){
		return ResponseEntity.status(status).body(ErrorStructure.create(status.value(), message, rootCause));
	}
	public ResponseEntity<Object> fieldErrors(HttpStatus status,String message,List<CustomFieldError> errors){
		return ResponseEntity.status(status).body(ErrorStructure.create(status.value(), message, errors));
	}
}
