package com.zensark.springdemo.controllers;

import com.zensark.springdemo.entities.Product;
import com.zensark.springdemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getHome(Model model){
        List<Product> allProducts = new ArrayList<>();
        productRepository.findAll().forEach(allProducts::add);
        model.addAttribute("products", allProducts);
        return "home";
    }

    @RequestMapping(value = "/product", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addProduct(Product product){
        productRepository.save(product);
        return "redirect:/home";
    }

    @GetMapping("/product/{id}/edit")
    public String editProductPage(@PathVariable int id, Model model){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            model.addAttribute("product", product.get());
        }else{
            model.addAttribute("error", "Not Found");
        }
        return "edit";
    }

    @RequestMapping(value = "/product/{id}/edit", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String editProduct(@PathVariable int id, Product product){
        product.setId(id);
        productRepository.save(product);
        return "redirect:/home";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
        return "redirect:/home";
    }
}
