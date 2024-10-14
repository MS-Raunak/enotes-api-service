package com.ms.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResourceNotFoundException extends Exception{
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
