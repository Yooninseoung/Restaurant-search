package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/insert")
    public String insert() {
        User user = new User( "22", "3", "4", "5", "6", "7");
        userRepository.save(user);
        return "index";
    }
}
