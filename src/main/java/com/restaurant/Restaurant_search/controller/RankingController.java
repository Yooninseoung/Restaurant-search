package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//식당 인기순 정렬 관련 기능
@Controller
public class RankingController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/ranking") //랭킹 페이지
    public String rank(Model model) {
        List<Restaurant> restaurants = restaurantService.rankRestaurantList();
        model.addAttribute("restaurant", restaurants);
        return "search/Rankingsearch";
    }
}
