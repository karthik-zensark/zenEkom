package com.zensark.springdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
     public Map<String, String> test(@RequestParam String userName, @RequestParam String email){
        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("email", email);
        return map;
     }
}
