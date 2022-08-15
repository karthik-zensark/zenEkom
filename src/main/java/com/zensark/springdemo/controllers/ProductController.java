package com.zensark.springdemo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensark.springdemo.entities.Product;
import com.zensark.springdemo.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductRepository repository;

	@GetMapping("/all")
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		repository.findAll().forEach(product -> products.add(product));
		return products;
	}
	
	@PostMapping("/create")
	public boolean createProduct(@RequestBody Product product) {
		repository.save(product);
		return true;
	}
}
