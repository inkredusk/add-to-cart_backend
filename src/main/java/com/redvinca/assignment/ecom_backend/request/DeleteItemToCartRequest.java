package com.redvinca.assignment.ecom_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItemToCartRequest {

	private Long cartId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	

	public DeleteItemToCartRequest(Long cartId) {
		super();
		this.cartId = cartId;
	}
	
	
}
