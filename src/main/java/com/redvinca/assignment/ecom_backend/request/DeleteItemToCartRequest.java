package com.redvinca.assignment.ecom_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItemToCartRequest {

	private Integer cartId;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
	

	public DeleteItemToCartRequest(Integer cartId) {
		super();
		this.cartId = cartId;
	}
	
	
}
