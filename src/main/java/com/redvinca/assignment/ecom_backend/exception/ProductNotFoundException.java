package com.redvinca.assignment.ecom_backend.exception;

public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -675661251666854040L;

	public ProductNotFoundException(String message) {
		super(message);

	}
}
