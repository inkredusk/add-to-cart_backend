package com.redvinca.assignment.ecom_backend.service;

import java.util.List;

import com.redvinca.assignment.ecom_backend.model.Product;

// Service interface for managing product operations.
public interface ProductService {

	//This method is responsible for adding a new product to the inventory.
    public Product createProduct(Product product);

    //This method fetches all products available in the inventory.
    public List<Product> getAllProducts();

    //This method fetches a specific product from the inventory based on its ID.
    public Product getProductById(Long id);
}
