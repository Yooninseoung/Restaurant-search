package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.LikeService;
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

    @Autowired
    LikeService likeService;

    @RequestMapping("/detailScreen") // 식당 세부 화면 조회
    public String detail(HttpServletRequest req, Model model, @PageableDefault(page = 1) Pageable pageable){
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함

        Restaurant restaurant = restaurantService.findRestaurant(Long.parseLong(restaurantId));//특정 식당의 정보를 갖고옴
        model.addAttribute("restaurant", restaurant);

        Page<Review> postsPages = reviewService.paging(pageable, Integer.parseInt(restaurantId));// 특정 식당 리뷰를 갖고옴
        model.addAttribute("review", postsPages);

        //특정 식당의 좋아요, 싫어요 수를 갖고옴. 유져는 각 식당마다 1번의 좋아요와 싫어요를 반영할 수 있음.
        int likeCount = likeService.countLike(Integer.parseInt(restaurantId));
        model.addAttribute("likeCount", likeCount);

        int unlikeCount = likeService.countUnLike(Integer.parseInt(restaurantId));
        model.addAttribute("unlikeCount", unlikeCount);


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
        Integer restaurantId = Integer.parseInt(req.getParameter("restaurantId")); //식당 id를 받아와 조회함

        //HttpSession session = req.getSession();
        //String userId = String.valueOf(session.getAttribute("userId"));
        String userId = "admin"; // 세션 기능 미완성, 대체제

        if(btn.equals("like")){ //좋아요 버튼이 눌림
            likeService.addLike(restaurantId, userId);
        }else{ // 싫어요 버튼이 눌림
            likeService.addUnLike(restaurantId, userId);
        }

        model.addAttribute("restaurant", restaurant);
        return "redirect:/restaurant/detailScreen?restaurantId=" + restaurantId;

    }

}
