package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import com.restaurant.Restaurant_search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }

    @GetMapping("/signUp")
    public String signupPage(){
        return "login/signup";
    }

    @PostMapping("/signUp") //회원 가입
    public String insert(User user) {
        System.out.print(user.getPhoneNumber());
        userService.insert(user);
        return "redirect:/index";
    }
}
