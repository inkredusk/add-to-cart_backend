package com.redvinca.assignment.ecom_backend.exception;
public class InsufficientStockException extends RuntimeException{
	 public InsufficientStockException(String message) {
	        super(message);
	    }
}