package com.redvinca.assignment.ecom_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redvinca.assignment.ecom_backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
