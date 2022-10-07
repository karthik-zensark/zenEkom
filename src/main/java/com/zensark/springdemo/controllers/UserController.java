package com.zensark.springdemo.controllers;

import com.zensark.springdemo.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final JdbcUserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public UserController(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        userDetailsManager = new JdbcUserDetailsManager(dataSource);
    }

    @GetMapping("/")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(AppUser user) {
        log.info("Creating new user with username {}", user.getUsername());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        User userDetails = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities);
        userDetailsManager.createUser(userDetails);
        return "redirect:/login";
    }
}
