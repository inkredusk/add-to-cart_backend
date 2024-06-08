package com.redvinca.assignment.ecom_backend.service;

import java.util.List;

import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.request.UpdateQuantityRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;

public interface CartService {


	public DeleteItemToCartResponse deleteItemToCart(DeleteItemToCartRequest cartRequest);

	public Cart addToCart(Long productId);

	public Cart updateCartQuantity(Long cartId, int quantity);

	public void removeFromCart(Long cartId);

	public List<Cart> getAllCartItems();

	public double getTotalPrice();

	public int getTotalQuantity();
	
	public MessageResponse updateQuantityIncreaseDecrease(UpdateQuantityRequest request);

	

}

