package com.ms.exception;

import java.util.Map;

public class ValidationException extends RuntimeException{
	Map<String, Object> errors;

	public ValidationException(Map<String, Object> errors) {
		super("validation failed");
		this.errors = errors;
	}
	
	public Map<String, Object> getErrors() {
		return errors;
	}
}
