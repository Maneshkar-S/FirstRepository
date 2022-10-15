package com.shoping.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.product.dto.Category;
import com.shoping.product.dto.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	//@Query("{Category.name:?0}")
	//List<Product> findByCategory(String category);

}
