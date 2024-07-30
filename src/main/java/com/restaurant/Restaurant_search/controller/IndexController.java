package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model){
        List<Restaurant> restaurants = restaurantService.restaurantList();
        model.addAttribute("restaurant", restaurants);
        return "index";
    }



    @GetMapping("/recommendMenu")
    public String recommendMenu(Model model){
        String [] menu =  {
                "김치 찌개", "된장 찌개", "비빔밥", "불고기", "삼겹살",
                "떡볶이", "김밥", "치킨", "갈비탕", "설렁탕",
                "칼국수", "냉면", "제육 볶음", "감자탕", "부대 찌개",
                "해물 파전", "닭갈비", "양념 치킨", "돼지 갈비", "순대 국밥",
                "닭발", "마라탕", "닭 볶음탕", "찜닭", "아구찜",
                "간장 게장", "탕후루", "핫도그", "피자", "해물탕"
        };

        int idx = (int)(Math.random()*30);

        model.addAttribute("menu", menu[idx]);


        return "restaurant/recommendFood";
    }

    @PostMapping("/search")
    public String searRestaurant(HttpServletRequest req, Model model){
        String searchName = req.getParameter("searchName");
        List<Restaurant> restaurants = restaurantService.searchRestaurants(searchName);
        model.addAttribute("restaurant", restaurants);
        model.addAttribute("searchName", searchName);
        return "search/searchPage";
    }
}
