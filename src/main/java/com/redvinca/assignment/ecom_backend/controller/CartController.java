package com.redvinca.assignment.ecom_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.serviceimpl.CartServiceImpl;

@RestController
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping("deleteItemToCart")
	public DeleteItemToCartResponse deleteItemFromCart(DeleteItemToCartRequest cartRequest) {
		return cartServiceImpl.deleteItemToCart(cartRequest);
	}

}
