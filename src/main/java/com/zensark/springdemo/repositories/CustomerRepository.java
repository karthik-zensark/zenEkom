package com.zensark.springdemo.repositories;

import com.zensark.springdemo.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
