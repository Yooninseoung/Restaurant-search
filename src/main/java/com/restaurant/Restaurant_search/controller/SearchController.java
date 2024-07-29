package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {

    private final RestaurantService restaurantService; // final 필드

    // 생성자 주입
    public SearchController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "address", required = false, defaultValue = "") String address,
            @RequestParam(name = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
            @RequestParam(name = "menu", required = false, defaultValue = "") String menu,
            Model model) {

        List<Restaurant> results = restaurantService.searchRestaurants(name, address, phoneNumber, menu);
        model.addAttribute("restaurants", results);
        return "restaurant/restaurantScreen"; // 검색 결과를 표시할 HTML 페이지
    }

    @GetMapping("/search/autocomplete")
    @ResponseBody
    public List<String> getAutoCompleteSuggestions(@RequestParam("query") String query) {
        return restaurantService.getAutoCompleteSuggestions(query);
    }
}
