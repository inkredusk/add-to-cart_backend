package com.redvinca.assignment.ecom_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.request.UpdateQuanatityRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;
import com.redvinca.assignment.ecom_backend.service.CartService;

@RestController
@RequestMapping("/v3/api-docs/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@RequestParam Long productId) {
		try {
			Cart addedCart = cartService.addToCart(productId);
			return ResponseEntity.ok(addedCart);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add item to cart");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCartQuantity(@RequestParam Long cartId, @RequestParam int quantity) {
		try {
			Cart updatedCart = cartService.updateCartQuantity(cartId, quantity);
			return ResponseEntity.ok(updatedCart);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update cart item quantity");
		}
	}

	@DeleteMapping("/remove")
	public ResponseEntity<?> removeFromCart(@RequestParam Long cartId) {
		try {
			cartService.removeFromCart(cartId);
			return ResponseEntity.ok("Item removed from cart");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove item from cart");
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllCartItems() {
		try {
			List<Cart> cartItems = cartService.getAllCartItems();
			return ResponseEntity.ok(cartItems);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve cart items");
		}
	}

	@GetMapping("/total-price")
	public ResponseEntity<?> getTotalPrice() {
		try {
			double totalPrice = cartService.getTotalPrice();
			return ResponseEntity.ok(totalPrice);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to calculate total price");
		}
	}

	@GetMapping("/total-quantity")
	public ResponseEntity<?> getTotalQuantity() {
		try {
			int totalQuantity = cartService.getTotalQuantity();
			return ResponseEntity.ok(totalQuantity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to calculate total quantity");
		}
	}

	@PostMapping("/updateQuantity")
	public ResponseEntity<MessageResponse> updateQuantity(@RequestBody UpdateQuanatityRequest request) {
		MessageResponse response = cartService.updateQuantity(request);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("deleteItemToCart")
	public DeleteItemToCartResponse deleteItemFromCart(DeleteItemToCartRequest cartRequest) {
		return cartService.deleteItemToCart(cartRequest);
	}
}
