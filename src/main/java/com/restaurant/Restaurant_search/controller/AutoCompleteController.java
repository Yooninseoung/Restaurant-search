package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoCompleteController {

    private final RestaurantService restaurantService; // final 필드

    // 생성자 주입
    public AutoCompleteController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam(name = "query") String query) {
        return restaurantService.getAutoCompleteSuggestions(query);
    }
}
