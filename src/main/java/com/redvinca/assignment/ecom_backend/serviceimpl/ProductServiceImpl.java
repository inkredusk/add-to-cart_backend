package com.redvinca.assignment.ecom_backend.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.constantvariables.Constants;
import com.redvinca.assignment.ecom_backend.exception.ProductNotFoundException;
import com.redvinca.assignment.ecom_backend.model.Product;
import com.redvinca.assignment.ecom_backend.repository.ProductRepository;
import com.redvinca.assignment.ecom_backend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    // Logger for logging the flow and important events in the service
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a new product. This method is responsible for adding a new product to the inventory.
     *
     * @param product the product to be created.
     * @return the created product.
     */
    @Override
    public Product createProduct(Product product) {
        logger.info(Constants.CREATE_PRODUCT_STARTED, product.getName());
        
        // Check if product details are valid
        if (product.getName() == null || product.getName().trim().isEmpty() || product.getPrice() == 0 || product.getStock() == 0) {
            throw new ProductNotFoundException(Constants.PRODUCT_DETAILS_INVALID);
        }
        
        // Save the product to the repository
        Product createdProduct = productRepository.save(product);
        logger.info(Constants.CREATE_PRODUCT_ENDED, product.getName());
        return createdProduct;
    }

    /**
     * Retrieves all products. This method fetches all products available in the inventory.
     *
     * @return a list of all products.
     */
    @Override
    public List<Product> getAllProducts() {
        logger.info(Constants.GET_ALL_PRODUCTS_STARTED);
        
        // Fetch all products from the repository
        List<Product> products = productRepository.findAll();
        logger.info(Constants.GET_ALL_PRODUCTS_ENDED, products.size());
        return products;
    }

    /**
     * Retrieves a product by its ID. This method fetches a specific product from the inventory based on its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return the product with the specified ID, or null if no such product exists.
     */
    @Override
    public Product getProductById(Long id) {
        logger.info(Constants.GET_PRODUCT_BY_ID_STARTED, id);
        
        // Fetch the product by ID from the repository
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            logger.info(Constants.PRODUCT_FOUND, id);
        } else {
            logger.error(Constants.PRODUCT_ID_INVALID);
        }
        logger.info(Constants.GET_PRODUCT_BY_ID_ENDED, id);
        return product;
    }
}
