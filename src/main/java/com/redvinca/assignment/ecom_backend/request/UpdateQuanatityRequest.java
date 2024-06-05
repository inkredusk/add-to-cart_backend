package com.redvinca.assignment.ecom_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuanatityRequest {

	private Long cartItemId;

	private Integer quantityChange; // positive for increase, negative for decrease
}
