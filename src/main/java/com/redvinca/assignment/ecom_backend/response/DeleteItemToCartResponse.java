package com.redvinca.assignment.ecom_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItemToCartResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public DeleteItemToCartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteItemToCartResponse(String message) {
		super();
		this.message = message;
	}

}
