package com.redvinca.assignment.ecom_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.exception.ProductNotFoundException;
import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.model.Product;
import com.redvinca.assignment.ecom_backend.repository.CartRepository;
import com.redvinca.assignment.ecom_backend.repository.ProductRepository;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.request.UpdateQuanatityRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	public Cart addToCart(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Product Not Found"));
		if (product.getStock() <= 0) {
			throw new IllegalArgumentException("Product is out of stock");
		}

		Optional<Cart> optionalCart = cartRepository.findByProductId(productId);
		if (optionalCart.isPresent()) {
			Cart cart = optionalCart.get();
			if (cart.getQuantity() < product.getStock()) {
				cart.setQuantity(cart.getQuantity() + 1);
				return cartRepository.save(cart);
			} else {
				throw new IllegalArgumentException("Quantity exceeds stock");
			}
		} else {
			Cart cart = new Cart();
			cart.setProduct(product);
			cart.setQuantity(1); // Default quantity
			return cartRepository.save(cart);
		}
	}

	public Cart updateCartQuantity(Long cartId, int quantity) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
		Product product = cart.getProduct();

		if (quantity > product.getStock()) {
			throw new IllegalArgumentException("Quantity exceeds stock");
		}

		cart.setQuantity(quantity);
		return cartRepository.save(cart);
	}

	public void removeFromCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}

	public List<Cart> getAllCartItems() {
		return cartRepository.findAll();
	}

	public double getTotalPrice() {
		List<Cart> cartItems = cartRepository.findAll();
		double totalPrice = 0.0;
		for (Cart cart : cartItems) {
			totalPrice += cart.getProduct().getPrice() * cart.getQuantity();
		}
		return totalPrice;
	}

	public int getTotalQuantity() {
		List<Cart> cartItems = cartRepository.findAll();
		int totalQuantity = 0;
		for (Cart cart : cartItems) {
			totalQuantity += cart.getQuantity();
		}
		return totalQuantity;
	}

	public DeleteItemToCartResponse deleteItemToCart(DeleteItemToCartRequest cartRequest) {

		Long cartId = cartRequest.getCartId();

		if (!cartRepository.existsById(cartId)) {
			throw new RuntimeException("Cart id not found..!");
		}
		cartRepository.deleteById(cartId);

		DeleteItemToCartResponse cartResponse = new DeleteItemToCartResponse();
		cartResponse.setMessage("Cart Item deleted Successfully");

		return cartResponse;
	}

	public MessageResponse updateQuantity(UpdateQuanatityRequest request) {
		// Find the cart item by ID
		Optional<Cart> cartItem = cartRepository.findById(request.getCartItemId());

		Cart cart = cartItem.get();

		Product product = cart.getProduct();

		if (!cartItem.isPresent()) {
			throw new ProductNotFoundException("Cart item not found");
		}

		// Update the quantity
		int newQuantity = cart.getQuantity() + request.getQuantityChange();
		
		if (newQuantity <= 0) {
			throw new ProductNotFoundException("Quantity cannot be less than zero");
		}

		if (newQuantity > product.getStock()) {
			throw new IllegalArgumentException("Quantity exceeds stock");
		}

		cart.setQuantity(newQuantity);

		// Save the updated cart item
		cart = cartRepository.save(cart);

		MessageResponse response = new MessageResponse();
		response.setMessage("Quantity updated Successfully..");
		return response;
	}

}
