package com.redvinca.assignment.ecom_backend.exception;

import lombok.AllArgsConstructor;

/**
 * Custom exception class for negative quantity scenario.
 */
@AllArgsConstructor
public class NegativeQuantityException extends RuntimeException {

	private String messString;
}
