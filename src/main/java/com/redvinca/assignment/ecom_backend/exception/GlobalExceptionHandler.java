package com.redvinca.assignment.ecom_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.redvinca.assignment.ecom_backend.response.MessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MessageResponse> handleGlobalException(ProductNotFoundException fx) {
		MessageResponse messageResponse = new MessageResponse(fx.getMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
	}
}
