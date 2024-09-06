package com.roomiereserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotExist extends RuntimeException {
	public NotExist(String message) {
		super(message);
	}
}