package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/writeForm") //리뷰 작성 화면 반환
    public String writeForm(HttpServletRequest req, Model model){
        model.addAttribute("restaurantId", req.getParameter("restaurantId"));
        return "restaurant/restaurantReviewWrite";
    }
    @PostMapping("/writeForm") //리뷰 작성 버튼
    public String writeForm(@RequestParam("file") MultipartFile file,
                            @ModelAttribute Review review,
                            @SessionAttribute(name = "userId", required = false) String userId) throws IOException {

        reviewService.writeReview(review, file, userId); //파일을 제외한 정보 작성


        return "redirect:/restaurant/detailScreen?restaurantId=" + review.getRestaurantId();
    }
}
