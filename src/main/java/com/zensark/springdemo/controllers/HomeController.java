package com.zensark.springdemo.controllers;

import com.zensark.springdemo.entities.Product;
import com.zensark.springdemo.entities.Employee;

import com.zensark.springdemo.repositories.ProductRepository;
import com.zensark.springdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private ProductRepository productRepository;
//    private EmployeeRepository employeeRepository;

    @GetMapping
    public String getHome(@AuthenticationPrincipal User user, Model model) {
    	model.addAttribute("username", user.getUsername());
    	List<Product> allProducts = new ArrayList<>();
    	productRepository.findAll().forEach(allProducts::add);
    	model.addAttribute("products", allProducts);
//    	List<Employee> allEmployees = new ArrayList<>();
//    	employeeRepository.findAll().forEach(allEmployees::add);
//    	model.addAttribute("employees", allEmployees);
//    	getUsername(user, model);	
//        getProducts(model);
//        getEmployees(model);
        return "home";
    }
    
    public void getUsername(@AuthenticationPrincipal User user, Model model) {
    }
    
    public void getEmployees(Model model){
    }
    
    public void getProducts(Model model){
    }

    @RequestMapping(value = "/product", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/home";
    }

    @GetMapping("/product/{id}/editProduct")
    public String editProductPage(@PathVariable int id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("error", "Not Found");
        }
        return "editProduct";
    }

    @RequestMapping(value = "/product/{id}/edit", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String editProduct(@PathVariable int id, Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/home";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "redirect:/home";
    }
}
