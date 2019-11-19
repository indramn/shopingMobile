package com.grokonez.spring.restapi.mongodb.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grokonez.spring.restapi.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	List<User> findByEmail(String email);

	User findUserByEmail(String email);

}
