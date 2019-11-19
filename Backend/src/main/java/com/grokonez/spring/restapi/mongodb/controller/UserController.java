package com.grokonez.spring.restapi.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.spring.restapi.mongodb.model.User;
import com.grokonez.spring.restapi.mongodb.repo.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest")
public class UserController {

	@Autowired
	UserRepository repository;

	@GetMapping("/users")
	public List<User> getAllCustomers() {
		System.out.println("Get all Customers...");
		List<User> objs = new ArrayList<>();
		return objs;
	}

	@GetMapping("/getAllUser")
	public List<User> getAllProducts() {
		System.out.println("Get all products...");
		List<User> objs = new ArrayList<>();
		repository.findAll().forEach(objs::add);
		return objs;
	}

	@PostMapping("/addUsers")
	public User addProduct(@RequestBody User user) {
		User _users = repository.save(new User(user.getUser_id(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPassword(), true));
		return _users;
	}

	@PostMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		User users = repository.findUserByEmail(user.getEmail());
		if (Objects.nonNull(users)) {
			users.setPassword(user.getPassword());
			return repository.save(users);
		}
		return null;
	}

	@PostMapping("/login")
	public List<User> userLogin(@RequestBody User user) {
		List<User> users = repository.findByEmail(user.getEmail());
		if (null != users && users.size() != 0
				&& users.stream().anyMatch(userobj -> userobj.getPassword().equals(user.getPassword()))) {
			return users;
		}
		return null;

	}
}
