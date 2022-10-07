package com.zensark.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
//        model.addAttribute("user", user);
        return "login";
	}
	
	@RequestMapping("/goToLogout")
	public String goToLogout() {
//        model.addAttribute("user", user);
        return "logout";
	}
}
