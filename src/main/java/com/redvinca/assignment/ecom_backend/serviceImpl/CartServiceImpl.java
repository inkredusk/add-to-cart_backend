package com.redvinca.assignment.ecom_backend.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.entity.CartEntity;
import com.redvinca.assignment.ecom_backend.exception.ProductNotFoundException;
import com.redvinca.assignment.ecom_backend.repository.CartRepository;
import com.redvinca.assignment.ecom_backend.request.UpdateQuanatityRequest;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;
import com.redvinca.assignment.ecom_backend.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public MessageResponse updateQuantity(UpdateQuanatityRequest request) {
		// Find the cart item by ID
		Optional<CartEntity> cartItem = cartRepository.findById(request.getCartItemId());

		if (!cartItem.isPresent()) {
			throw new ProductNotFoundException("Cart item not found");
		}

		CartEntity cart = cartItem.get();

		// Update the quantity
		int newQuantity = cart.getProductQuantity() + request.getQuantityChange();
		if (newQuantity <= 0) {
			throw new ProductNotFoundException("Quantity cannot be less than zero");
		}

		cart.setProductQuantity(newQuantity);

		// Save the updated cart item
		cart = cartRepository.save(cart);

		MessageResponse response = new MessageResponse();
		response.setMessage("Quantity updated Successfully..");
		return response;
	}

}
