package com.redvinca.assignment.ecom_backend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductNotFoundException extends RuntimeException {

 private String messString;
}
