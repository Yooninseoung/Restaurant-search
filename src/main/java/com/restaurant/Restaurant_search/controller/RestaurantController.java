package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/restaurant")
@Controller
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping("/detailScreen") // 식당 세부 화면 조회
    public String detail(HttpServletRequest req, Model model){
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함
        Restaurant restaurant = restaurantService.findRestaurant(Long.parseLong(restaurantId));
        model.addAttribute("restaurant", restaurant);
        return "restaurant/restaurantScreen";

    }


}
