package com.redvinca.assignment.ecom_backend.service;

import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;

public interface CartService {
	
	public DeleteItemToCartResponse deleteItemToCart(DeleteItemToCartRequest cartRequest);

}
