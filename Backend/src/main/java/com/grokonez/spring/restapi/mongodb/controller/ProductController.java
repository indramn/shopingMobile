package com.grokonez.spring.restapi.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.spring.restapi.mongodb.model.Product;
import com.grokonez.spring.restapi.mongodb.repo.ProdcutRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest")
public class ProductController {

	@Autowired
	ProdcutRepository repository;

	@GetMapping("/products")
	public List<Product> getAllCustomers() {
		System.out.println("Get all Customers...");
		List<Product> products = new ArrayList<>();
		return products;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		System.out.println("Get all products...");
		List<Product> products = new ArrayList<>();
		repository.findAll().forEach(products::add);
		return products;
	}

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		Product _product = repository.save(new Product(product.getProductId(), product.getProductName(),
				product.getProductCategory(), product.getProductSeller(), product.getProductImage(),
				product.getProductRating(), product.getProductPrice(), product.getProductDescription(),
				product.isBestProduct(), product.isTopProduct()));
		return _product;
	}

	@GetMapping("products/{id}")
	public List<Product> findByName(@PathVariable String id) {

		List<Product> products = repository.findByProductId(id);
		return products;
	}

}
