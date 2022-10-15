package com.shoping.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.product.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	//@Query("{Category.name:?0}")
	//List<Product> findByCategory(String category);

}
