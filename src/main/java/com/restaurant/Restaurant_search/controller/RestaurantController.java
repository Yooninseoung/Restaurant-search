package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.RestaurantService;
import com.restaurant.Restaurant_search.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping("/detailScreen") // 식당 세부 화면 조회
    public String detail(HttpServletRequest req, Model model, @PageableDefault(page = 1) Pageable pageable){
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함
        Restaurant restaurant = restaurantService.findRestaurant(Long.parseLong(restaurantId));//특정 식당의 정보를 갖고옴
        model.addAttribute("restaurant", restaurant);

        Page<Review> postsPages = reviewService.paging(pageable, Integer.parseInt(restaurantId));// 특정 식당 리뷰를 갖고옴
        model.addAttribute("review", postsPages);

        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());
        model.addAttribute("startPage", startPage); // 리뷰 리스트, 페이지 처음과 마지막 이동 시 사용하는 변수를 전달
        model.addAttribute("endPage", endPage);

        return "restaurant/restaurantScreen";

    }



    @RequestMapping(value="/push/{btn}", method= RequestMethod.GET) //btn에 따른 좋아요, 싫어요 기능
    public String pushButton(@PathVariable("btn") String btn, HttpServletRequest req, Model model){
        Restaurant restaurant = null;
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함

        if(btn.equals("like")){ //좋아요 버튼이 눌림
            restaurant = restaurantService.like(Long.parseLong(restaurantId));
        }else{ // 싫어요 버튼이 눌림
            restaurant = restaurantService.dislike(Long.parseLong(restaurantId));
        }

        model.addAttribute("restaurant", restaurant);
        return "redirect:/restaurant/detailScreen?restaurantId=" + restaurantId;

    }


}
