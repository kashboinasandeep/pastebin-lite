package com.pastebinlite.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> badRequest(IllegalArgumentException ex) {
		return Map.of("error", ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> notFound(RuntimeException ex) {
		return Map.of("error", ex.getMessage());
	}
}
