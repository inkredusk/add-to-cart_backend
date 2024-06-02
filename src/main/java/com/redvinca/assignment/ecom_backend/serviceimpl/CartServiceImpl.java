package com.redvinca.assignment.ecom_backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.repository.CartListRepository;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartListRepository cartListRepository;

	@Override
	public DeleteItemToCartResponse deleteItemToCart(DeleteItemToCartRequest cartRequest) {
		
		Integer cartId=cartRequest.getCartId();
		
		if(!cartListRepository.existsById(cartId)) {
			throw new RuntimeException("Cart id not found..!");
		}
		cartListRepository.deleteById(cartId);
		
		DeleteItemToCartResponse cartResponse=new DeleteItemToCartResponse();
		cartResponse.setMessage("Cart Item deleted Successfully");
		
		return cartResponse;
	}

}
