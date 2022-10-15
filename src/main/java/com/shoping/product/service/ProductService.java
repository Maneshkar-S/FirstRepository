package com.shoping.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.product.config.ProductConfiguration;
import com.shoping.product.dto.Product;
import com.shoping.product.exception.CurrencyNotValidException;
import com.shoping.product.exception.OfferNotValidException;
import com.shoping.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductConfiguration productConfiguration;
	
	/*
	 * public ProductService(ProductRepository productRepository) {
	 * this.productRepository=productRepository; }
	 */

	public String addProduct(Product product) {
		if(product.getPrice()==0 && product.getDiscount()>0) {
			throw new OfferNotValidException("No discount is allowed at 0 product price");
		}
		//List<String> validCurrencies = new ArrayList<>();
		//validCurrencies.add("INR");
		//validCurrencies.add("USD");
		//validCurrencies.add("EUR");
		if(!productConfiguration.getCurrencies().contains(product.getCurrency())){
			throw new CurrencyNotValidException("Invalid Currency. Valid Currencies are : "+productConfiguration.getCurrencies());
		}
		productRepository.save(product);
		return "success";
	}

	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> productCategoryList(String category) {
		//return productRepository.findByCategory(category);
		return null;
	}

	public Product productById(Integer id) {
		return productRepository.findById(id).get();
	}

	public String updateProduct(Product product) {
		for (Product prod : productRepository.findAll()) {
			if (prod.getId().equals(product.getId())) {
				productRepository.save(product);
				return "product updated successfully";
			}
		}
		return "product updation failed";
	}

	public String deleteproductById(Integer id) {
		for (Product prod : productRepository.findAll()) {
			if (prod.getId().equals(id)) {
				productRepository.deleteById(id);
				return "product deleted successfully";
			}
		}
		return "product deletion failed";
	}

}
