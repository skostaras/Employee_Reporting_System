package com.stefanosk27.reporting.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class StorageFailedException extends RuntimeException {

	private static final long serialVersionUID = -6771987193983064830L;

	public StorageFailedException() {
		super();
	}

	public StorageFailedException(String message) {
		super(message);
	}

	public StorageFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
