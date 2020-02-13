package com.example.demo.Exception;



public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private ExceptionResponse exceptionResponse;
	public ExceptionResponse getExceptionResponse() {
		return exceptionResponse;
	}
	public void setExceptionResponse(ExceptionResponse exceptionResponse) {
		this.exceptionResponse = exceptionResponse;
	}
	public ResourceNotFoundException() {
		super();
	}
	public ResourceNotFoundException(String message) {
		super();
		this.exceptionResponse=new ExceptionResponse(message);
		
	}

}
