package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.User;
import com.restaurant.Restaurant_search.repository.UserRepository;
import com.restaurant.Restaurant_search.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("password") String password,
                        HttpServletRequest req,
                        Model model) {
        boolean success = userService.authenticate(userId, password);
        if (success) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId);
            session.setMaxInactiveInterval(1800);// Session의 유효 시간 설정 (1800초 = 30분)
            if(userId.equals("admin")){
                return "redirect:/manage/adminPage";
            }
            return "redirect:/index"; // 로그인 성공 시 index.html 페이지로 이동
        }
        else {

            return "login/login"; // 로그인 실패 시 다시 login.html 페이지로 이동
        }
    }


    @GetMapping("/signUp")
    public String signupPage(){
        return "login/signup";
    }

    @PostMapping("/signUp") //회원 가입
    public String insert(User user) {
        userService.insert(user);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }

    @GetMapping("/searchUser") //userId로 user를 받아옴(관리자 .. 유져 검색 기능)
    public String searchUser(HttpServletRequest req, Model model){
        String searchId = req.getParameter("searchId");
        User user = userService.searchUser(searchId);
        model.addAttribute("user", user);
        System.out.print(searchId);

        return "admin/userManagePage";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(HttpServletRequest req){
        String userId = req.getParameter("userId");
        userService.deleteUserById(userId);

        return "admin/adminPage";
    }

    @PostMapping("/updateUser") //회원 정보 수정
    public String updateUser(User user) {
        userService.insert(user);
        return "admin/adminPage";
    }


}
