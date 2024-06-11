package com.redvinca.assignment.ecom_backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.constants.Constants;
import com.redvinca.assignment.ecom_backend.model.Product;
import com.redvinca.assignment.ecom_backend.service.IProductService;

@RestController
@RequestMapping
public class ProductController {
	
	@Value("${product.api.url}")
    private String productApiUrl;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService iProductService;

	/**
	 * Creates a new product.
	 * 
	 * @param product the product to be created.
	 * @return the response entity with the created product or an error message.
	 */
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		logger.info(Constants.CONTROLLER_CREATE_PRODUCT_STARTED, product.getName());
		try {
			Product createdProduct = iProductService.createProduct(product);
			logger.info(Constants.CONTROLLER_PRODUCT_CREATED_SUCCESSFULLY, createdProduct.getName());
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			logger.error(Constants.PRODUCT_DETAILS_INVALID, e);
			return ResponseEntity.status(500).body(Constants.PRODUCT_DETAILS_INVALID);
		}
	}

	/**
	 * Retrieves all products.
	 * 
	 * @return the response entity with the list of products.
	 */
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info(Constants.CONTROLLER_GET_ALL_PRODUCTS_STARTED);
		List<Product> products = iProductService.getAllProducts();
		logger.info(Constants.CONTROLLER_RETRIEVED_PRODUCTS, products.size());
		return ResponseEntity.ok(products);
	}

	/**
	 * Retrieves a product by its ID.
	 * 
	 * @param id the ID of the product to be retrieved.
	 * @return the response entity with the product or not found status.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		logger.info(Constants.CONTROLLER_GET_PRODUCT_BY_ID_STARTED, id);
		Product product = iProductService.getProductById(id);
		if (product != null) {
			logger.info(Constants.CONTROLLER_PRODUCT_FOUND, id);
			return ResponseEntity.ok(product);
		} else {
			logger.error(Constants.PRODUCT_ID_INVALID);
			return ResponseEntity.status(500).body(Constants.PRODUCT_ID_INVALID);
		}
	}
}
