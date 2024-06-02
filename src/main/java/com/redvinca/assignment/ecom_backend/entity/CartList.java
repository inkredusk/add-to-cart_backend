package com.redvinca.assignment.ecom_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart_list")
public class CartList {

	private Integer cartId;
	
	private String productName;
	
	private String productDescription;
	
	private Double productPrice;
	
	private Integer productQuantity;
	
	
}
