package com.grokonez.spring.restapi.mongodb.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grokonez.spring.restapi.mongodb.model.Product;

public interface ProdcutRepository extends MongoRepository<Product, String>{

	List<Product> findByProductId(String id);

}
