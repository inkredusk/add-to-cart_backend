package com.redvinca.assignment.ecom_backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.constants.Constants;
import com.redvinca.assignment.ecom_backend.model.Cart;
import com.redvinca.assignment.ecom_backend.request.DeleteItemToCartRequest;
import com.redvinca.assignment.ecom_backend.request.UpdateQuantityRequest;
import com.redvinca.assignment.ecom_backend.response.DeleteItemToCartResponse;
import com.redvinca.assignment.ecom_backend.response.MessageResponse;
import com.redvinca.assignment.ecom_backend.serviceimpl.CartServiceImpl;

@RestController
@RequestMapping("${cart.api.url}") // Base URL from properties file
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartServiceImpl cartServiceImpl;

    /**
     * Adds a product to the cart.
     * 
     * @param productId the ID of the product to be added.
     * @return the response entity with the added cart item or error message.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Long productId) {
        logger.info(Constants.CONTROLLER_ADD_TO_CART_STARTED, productId);
        try {
            Cart addedCart = cartServiceImpl.addToCart(productId);
            logger.info(Constants.CONTROLLER_ADD_TO_CART_SUCCESS, productId);
            return ResponseEntity.ok(addedCart);
        } catch (Exception e) {
            logger.error(Constants.PRODUCT_ID_INVALID, productId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.PRODUCT_ID_INVALID);
        }
    }

    /**
     * Retrieves all items in the cart.
     * 
     * @return the response entity with the list of cart items or error message.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllCartItems() {
        logger.info(Constants.CONTROLLER_GET_ALL_CART_ITEMS_STARTED);
        try {
            List<Cart> cartItems = cartServiceImpl.getAllCartItems();
            logger.info(Constants.CONTROLLER_GET_ALL_CART_ITEMS_SUCCESS, cartItems.size());
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            logger.error(Constants.CONTROLLER_GET_ALL_CART_ITEMS_FAILURE, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.CONTROLLER_GET_ALL_CART_ITEMS_FAILURE);
        }
    }

    /**
     * Calculates the total price of all items in the cart.
     * 
     * @return the response entity with the total price or error message.
     */
    @GetMapping("/total-price")
    public ResponseEntity<?> getTotalPrice() {
        logger.info(Constants.CONTROLLER_GET_TOTAL_PRICE_STARTED);
        try {
            double totalPrice = cartServiceImpl.getTotalPrice();
            logger.info(Constants.CONTROLLER_GET_TOTAL_PRICE_SUCCESS, totalPrice);
            return ResponseEntity.ok(totalPrice);
        } catch (Exception e) {
            logger.error(Constants.CONTROLLER_GET_TOTAL_PRICE_FAILURE, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.CONTROLLER_GET_TOTAL_PRICE_FAILURE);
        }
    }

    /**
     * Calculates the total quantity of all items in the cart.
     * 
     * @return the response entity with the total quantity or error message.
     */
    @GetMapping("/total-quantity")
    public ResponseEntity<?> getTotalQuantity() {
        logger.info(Constants.CONTROLLER_GET_TOTAL_QUANTITY_STARTED);
        try {
            int totalQuantity = cartServiceImpl.getTotalQuantity();
            logger.info(Constants.CONTROLLER_GET_TOTAL_QUANTITY_SUCCESS, totalQuantity);
            return ResponseEntity.ok(totalQuantity);
        } catch (Exception e) {
            logger.error(Constants.CONTROLLER_GET_TOTAL_QUANTITY_FAILURE, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.CONTROLLER_GET_TOTAL_QUANTITY_FAILURE);
        }
    }

    /**
     * Updates the quantity of a cart item.
     * 
     * @param request the request containing the cart item ID and the quantity
     *                change.
     * @return the response entity with a success message or error message.
     */
    @PostMapping("/updateQuantity")
    public MessageResponse updateQuantity(@RequestBody UpdateQuantityRequest request) {
        logger.info(Constants.CONTROLLER_UPDATE_QUANTITY_STARTED, request.getCartItemId(), request.getQuantityChange());
        MessageResponse response = cartServiceImpl.updateQuantityIncreaseDecrease(request);
        logger.info(Constants.CONTROLLER_UPDATE_QUANTITY_ENDED, request.getCartItemId());
        return response;
    }

    /**
     * Deletes an item from the cart.
     * 
     * @param cartRequest the request containing the cart ID.
     * @return the response entity with a success message or error message.
     */
    @PostMapping("/deleteItemToCart")
    public DeleteItemToCartResponse deleteItemFromCart(@RequestBody DeleteItemToCartRequest cartRequest) {
        logger.info(Constants.CONTROLLER_DELETE_ITEM_FROM_CART_STARTED, cartRequest.getCartId());
        DeleteItemToCartResponse response = cartServiceImpl.deleteItemToCart(cartRequest);
        logger.info(Constants.CONTROLLER_DELETE_ITEM_FROM_CART_ENDED, cartRequest.getCartId());
        return response;
    }
}
