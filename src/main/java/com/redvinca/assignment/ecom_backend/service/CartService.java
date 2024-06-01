package com.redvinca.assignment.ecom_backend.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.model.Product;
import com.redvinca.assignment.ecom_backend.repository.CartRepository;
import com.redvinca.assignment.ecom_backend.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;


	public Cart addToCart(Long productId) {
		Product product  = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("Product Not Found"));
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
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
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


}
