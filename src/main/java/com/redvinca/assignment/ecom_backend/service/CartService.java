package com.redvinca.assignment.ecom_backend.service;

import java.util.List;

import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;

public interface CartService {
	
	public abstract DeleteItemToCartResponse deleteItemToCart(DeleteItemToCartRequest cartRequest);
	public abstract Cart addToCart(Long productId);
	public abstract Cart updateCartQuantity(Long cartId, int quantity);
	public abstract void removeFromCart(Long cartId);
	public abstract List<Cart> getAllCartItems();
	public abstract double getTotalPrice();
	public abstract int getTotalQuantity();
	

}
