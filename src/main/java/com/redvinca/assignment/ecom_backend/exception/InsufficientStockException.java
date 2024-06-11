package com.redvinca.assignment.ecom_backend.exception;

import lombok.AllArgsConstructor;

/**
 * Custom exception class for insufficient stock scenario.
 */
@AllArgsConstructor
public class InsufficientStockException extends RuntimeException {

	private String messString;
}
