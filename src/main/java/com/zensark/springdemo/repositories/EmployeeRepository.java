package com.zensark.springdemo.repositories;

import com.zensark.springdemo.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
