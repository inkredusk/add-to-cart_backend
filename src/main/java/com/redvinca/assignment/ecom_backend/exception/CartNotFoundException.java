package com.redvinca.assignment.ecom_backend.exception;

import lombok.AllArgsConstructor;

/**
 * Custom exception class for cart not found scenario.
 */
@AllArgsConstructor
public class CartNotFoundException extends RuntimeException {

	private String messString;

}
