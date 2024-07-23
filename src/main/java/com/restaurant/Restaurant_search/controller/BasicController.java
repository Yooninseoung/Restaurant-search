package com.restaurant.Restaurant_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
    @RequestMapping("/index")
    public String home(){ // 홈페이지 반환
        return "index";
    }

}
