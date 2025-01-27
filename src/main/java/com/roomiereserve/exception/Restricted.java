package com.roomiereserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class Restricted extends RuntimeException {
	public Restricted(String message) {
		super(message);
	}
}