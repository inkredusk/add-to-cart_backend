package com.redvinca.assignment.ecom_backend.request;




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
