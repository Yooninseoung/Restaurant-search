package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService; // final 필드

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public String insert(User user) {
        userService.insert(user);
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        boolean success = userService.authenticate(username, password);
        if (success) {
            return "index"; // 로그인 성공 시 index.html 페이지로 이동
        }
        else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            return "login/login"; // 로그인 실패 시 다시 login.html 페이지로 이동
        }
    }

    // 로그인 페이지로 이동하기 위한 메서드 추가
    @PostMapping ("/login")
    public String showLoginPage() {
        return "login/login"; // 로그인 페이지 반환
    }

    @PostMapping ("/signUp")
    public String showSignUpPage() {
        return "login/signup"; // 회원가입 페이지 반환
    }
}
