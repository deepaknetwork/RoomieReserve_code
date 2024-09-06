package com.roomiereserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExist extends RuntimeException {

	public AlreadyExist(String message) {
		super(message);
	}
}