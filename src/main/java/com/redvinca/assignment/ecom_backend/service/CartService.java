package com.redvinca.assignment.ecom_backend.service;

import com.redvinca.assignment.ecom_backend.request.UpdateQuanatityRequest;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;

public interface CartService {

	public MessageResponse updateQuantity(UpdateQuanatityRequest request);
}
