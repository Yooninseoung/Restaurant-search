package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


        int blockLimit = 3; ////페이지 버튼을 한번에 최대 5개를 보여줌
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());
        model.addAttribute("startPage", startPage); // 리뷰 리스트, 페이지 처음과 마지막 이동 시 사용하는 변수를 전달
        model.addAttribute("endPage", endPage);

        return "restaurant/restaurantScreen";

    }


    @RequestMapping(value="/push/{where}/{btn}", method= RequestMethod.GET) //btn에 따른 좋아요, 싫어요 기능
    public String pushButton(@PathVariable("where") String where,
                             @PathVariable("btn") String btn,
                             HttpServletRequest req, Model model,
                             @SessionAttribute(name = "userId", required = false) String userId){

        Restaurant restaurant = null;
        Integer restaurantId = Integer.parseInt(req.getParameter("restaurantId")); //식당 id를 받아와 조회함


        CommonDataID commonDataID = new CommonDataID(userId, restaurantId); //좋아요, 싫어요 table의 기본키

        boolean flag;

// 좋아요 혹은 싫어요 유무에 따라 ON/OFF 기능을 지원
        if(btn.equals("like")){ //입력이 좋아요 버튼
            flag = likeService.existLikeData(restaurantId, userId); //좋아요 테이블에 데이터가 존재하는지 확인
            LikeData likeData = new LikeData(commonDataID); // entity객체
            if(flag){ //true : 데이터가 있음 -> 데이터를 삭제
                likeService.deleteLike(likeData);
            }else {
                likeService.addLike(likeData);
            }
        }else{ //입력이 싫어요 버튼
            flag = likeService.existUnlikeData(restaurantId, userId); //싫어요 테이블에 존재하는지 확인
            UnLikeData unlikeData = new UnLikeData(commonDataID);
            if(flag){ //true : 데이터가 있음 -> 데이터를 삭제
                likeService.deleteUnLike(unlikeData);
            }else {
                likeService.addUnLike(unlikeData);
            }

        }

        if(where.equals("index")){ //좋아요 싫어요 버튼을 누를 수 있는 화면 3곳 : 각 요청한 곳으로 반환
            return "redirect:/index";
        }else if(where.equals("detail")){
            model.addAttribute("restaurant", restaurant);
            return "redirect:/restaurant/detailScreen?restaurantId=" + restaurantId;
        }else if(where.equals("ranking")){
            return "redirect:/ranking";
        }else if(where.equals("search")){
            return "redirect:/index";
        }else{
            return "redirect:/restaurant/restaurantAllPage";
        }



    }

    @GetMapping("/restaurantAllPage")
    public String showAllRestaurantPage(Model model, @PageableDefault(page = 1) Pageable pageable){

        Page<Restaurant> postsPages = restaurantService.paging(pageable);
        model.addAttribute("restaurant", postsPages);

        int blockLimit = 10; //페이지 버튼 한번에 10개씩 보여줌
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());
        model.addAttribute("startPage", startPage); // 식당 리스트, 페이지 처음과 마지막 이동 시 사용하는 변수를 전달
        model.addAttribute("endPage", endPage);

        return "restaurant/restaurantAllPage";
    }


}
