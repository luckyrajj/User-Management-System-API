package com.example.ums.util;

public class ErrorStructure<T> {

	private int status;
	private String message;
	private T rootCause;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getRootCause() {
		return rootCause;
	}
	public void setRootCause(T rootCause) {
		this.rootCause = rootCause;
	}
	
	public static<T> ErrorStructure<T> create(int status,String message,T rootCause) {
		ErrorStructure<T> errorStructure=new ErrorStructure<T>();
		errorStructure.setStatus(status);
		errorStructure.setMessage(message);
		errorStructure.setRootCause(rootCause);
		return errorStructure;
	}
}
