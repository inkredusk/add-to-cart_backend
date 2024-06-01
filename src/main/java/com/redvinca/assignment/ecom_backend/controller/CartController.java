package com.redvinca.assignment.ecom_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.request.UpdateQuanatityRequest;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;
import com.redvinca.assignment.ecom_backend.serviceImpl.CartServiceImpl;

@RestController
public class CartController {

	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping("/update-Quantity")
	public  ResponseEntity<MessageResponse> updateQuantity(@RequestBody UpdateQuanatityRequest request){
		MessageResponse response = cartServiceImpl.updateQuantity(request);
		return ResponseEntity.ok().body(response);
	}
}
