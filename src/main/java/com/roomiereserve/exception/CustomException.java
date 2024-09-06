package com.roomiereserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleALLException(Exception ex, WebRequest request) throws Exception {
		ExceptionDetails exceptiondetails = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<Object>(exceptiondetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
