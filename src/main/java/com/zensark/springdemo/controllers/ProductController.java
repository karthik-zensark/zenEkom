package com.zensark.springdemo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zensark.springdemo.entities.Product;
import com.zensark.springdemo.repositories.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/all")
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}
	
	@GetMapping
	public String getproducts(@AuthenticationPrincipal User user, Model model) {
	    model.addAttribute("username", user.getUsername());
		List<Product> allProducts = new ArrayList<>();
		productRepository.findAll().forEach(product -> allProducts.add(product));
		model.addAttribute("products", allProducts);
		return "products";
	}
	
	@PostMapping("/creat")
	public boolean createProduct(@RequestBody Product product) {
		productRepository.save(product);
		return true;
	}
	
	@RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/editProduct")
    public String editProductPage(@PathVariable int id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("error", "Not Found");
        }
        return "editProduct";
    }

    @RequestMapping(value = "/{id}/edit", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String editProduct(@PathVariable int id, Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}
