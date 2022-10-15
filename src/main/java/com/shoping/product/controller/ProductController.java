package com.shoping.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.product.dto.Product;
import com.shoping.product.service.ProductService;

@RestController
@RequestMapping("/v1")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	/*
	 * public ProductController(ProductService productService) { this.productService
	 * = productService; }
	 */
	
	@PostMapping("/addProduct")
	ResponseEntity<Product> addProduct(@RequestBody Product product){
		logger.info("adding product");
		String status = productService.addProduct(product);
		logger.info("Product added status - {}", status);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@GetMapping("/productList")
	ResponseEntity<List<Product>> productList(){
		List<Product> listAllProducts = productService.listAllProducts();
		//logger.info("Product added status - {}", status);
		//return ResponseEntity.status(HttpStatus.OK).body(List<Product>);
		return ResponseEntity.status(HttpStatus.OK).body(listAllProducts);
	}
	
	@GetMapping("/productList/{category}")
	ResponseEntity<List<Product>> productCategoryList(@PathVariable String category){
		List<Product> productCategoryList = productService.productCategoryList(category);
		//logger.info("Product added status - {}", status);
		//return ResponseEntity.status(HttpStatus.OK).body(List<Product>);
		return ResponseEntity.status(HttpStatus.OK).body(productCategoryList);
	}
	
	@GetMapping("/product/{id}")
	ResponseEntity<Product> productById(@PathVariable Integer id){
		Product productById = productService.productById(id);
		//logger.info("Product added status - {}", status);
		//return ResponseEntity.status(HttpStatus.OK).body(List<Product>);
		return ResponseEntity.status(HttpStatus.OK).body(productById);
	}
	
	@PutMapping("/productUpdate")
	ResponseEntity<String> updateProduct(@PathVariable Product product){
		String updateProduct = productService.updateProduct(product);
		//logger.info("Product added status - {}", status);
		//return ResponseEntity.status(HttpStatus.OK).body(List<Product>);
		return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
	}
	
	@DeleteMapping("/product/{id}")
	ResponseEntity<String> deleteProductById(@PathVariable Integer id){
		String productById = productService.deleteproductById(id);
		//logger.info("Product added status - {}", status);
		//return ResponseEntity.status(HttpStatus.OK).body(List<Product>);
		return ResponseEntity.status(HttpStatus.OK).body(productById);
	}
}
