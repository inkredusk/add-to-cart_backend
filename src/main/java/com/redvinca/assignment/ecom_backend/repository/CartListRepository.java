package com.redvinca.assignment.ecom_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.redvinca.assignment.ecom_backend.entity.CartList;
@Repository
public interface CartListRepository extends JpaRepository<CartList, Integer>{

	
}
