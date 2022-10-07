package com.zensark.springdemo.controllers;

import com.zensark.springdemo.entities.Employee;
import com.zensark.springdemo.entities.Product;
import com.zensark.springdemo.repositories.EmployeeRepository;
import com.zensark.springdemo.repositories.ProductRepository;
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
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String getemployee(@AuthenticationPrincipal User user, Model model){
    	model.addAttribute("username", user.getUsername());
        List<Employee> allEmployees = new ArrayList<>();
        employeeRepository.findAll().forEach(allEmployees::add);
        model.addAttribute("employees", allEmployees);
        return "employees";
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addemployee(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable int id, Model model){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            model.addAttribute("employee", employee.get());
        }else{
            model.addAttribute("error", "Not Found");
        }
        return "editEmployee";
    }

    @RequestMapping(value = "/{id}/edit", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String editProduct(@PathVariable int id, Employee employee){
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/delete")
    public String deleteemployee(@PathVariable int id){
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}
