package com.zensark.springdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zensark.springdemo.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
