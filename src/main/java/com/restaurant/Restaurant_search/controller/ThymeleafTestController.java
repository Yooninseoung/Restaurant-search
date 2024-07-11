package com.restaurant.Restaurant_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ThymeleafTestController {
    @RequestMapping("/freeBoard")
    public String freeBoard(Model model){
        ArrayList<String> post = new ArrayList<>();
        post.add("1번째 글입니다.");
        post.add("2번째 글입니다.");
        post.add("3번째 글입니다.");
        post.add("4번째 글입니다.");
        model.addAttribute("post", post);
        return "board/freeBoard";
    }

    @RequestMapping("/restaurantScreen")
    public String restaurantScreen(Model model){

        String restaurant[] = {"가장 맛있는 돈까스", "09~18시", "흥업면 17", "033-123-3321", "3.8"};


        ArrayList<String> post = new ArrayList<>();
        post.add("1번째 글입니다.");
        post.add("2번째 글입니다.");
        post.add("3번째 글입니다.");
        post.add("4번째 글입니다.");

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("post", post);
        return "restaurant/restaurantScreen";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login/login";
    }


}
