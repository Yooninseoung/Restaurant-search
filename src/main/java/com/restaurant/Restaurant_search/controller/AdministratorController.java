package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.JavaReadCsvService;
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

// 관리자 권한의 기능을 처리
@Controller
@RequestMapping("/manage")
public class AdministratorController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    ReviewService reviewService;

    @Autowired
    LikeService likeService;

    @Autowired
    private JavaReadCsvService javaReadCsvService;
    @RequestMapping("/csvToDb") //원주시 식당 csv 파일을 db에 저장
    public String csv(Model model){
        javaReadCsvService.readCSV();
        return "redirect:/index";
    }

    @RequestMapping("/adminPage")
    public String adminPage(){
        return "admin/adminPage";
    }

    @GetMapping("/user")
    public String manageUser(){

        return "admin/userManagePage";
    }

    @GetMapping("/restaurant")
    public String manageRestaurant(){

        return "admin/restaurantAdd";
    }

    @GetMapping("/board")
    public String manageBoard(){

        return "admin/boardManagePage";
    }

    @PostMapping("/restaurantAdd") //식당 등록
    public String addRestaurant(@RequestParam("file") MultipartFile file,
                                @ModelAttribute Restaurant restaurant) throws IOException {

        restaurantService.writeRestaurant(restaurant, file);


        return "admin/adminPage";
    }

    @PostMapping("/searchRestaurant") //관리자 화면에서 검색
    public String searRestaurant(HttpServletRequest req, Model model){
        String searchName = req.getParameter("searchName");
        List<Restaurant> restaurants = restaurantService.searchRestaurants(searchName);
        model.addAttribute("restaurant", restaurants);
        model.addAttribute("searchName", searchName);
        return "admin/restaurantManagePage";
    }

    @RequestMapping("/restaurantEdit") // 관리자 : 식당 세부 화면 조회
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

        return "admin/restaurantEdit";

    }

    @PostMapping("/restaurantModify")
    public String restaurantModify(HttpServletRequest req,
                                   @RequestParam("file") MultipartFile file) throws IOException{
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함

        Restaurant restaurant = restaurantService.findRestaurant(Long.parseLong(restaurantId));//특정 식당의 정보를 갖고옴

        restaurant.setRestaurantName(req.getParameter("restaurantName"));
        restaurant.setPhoneNumber(req.getParameter("phoneNumber"));
        restaurant.setMenu(req.getParameter("menu"));
        restaurant.setAddress(req.getParameter("address"));

        restaurantService.modifyRestaurant(restaurant, file);

        return "admin/adminPage";
    }

    @GetMapping("/deleteRestaurant")
    public String deleteRestaurant(HttpServletRequest req){
        String restaurantId = req.getParameter("restaurantId"); //식당 id를 받아와 조회함
        restaurantService.removeRestaurant(Long.parseLong(restaurantId));//특정 식당의 정보를 삭제
        return "admin/adminPage";
    }

    @GetMapping("/deleteReview")
    public String deleteReview(HttpServletRequest req){
        String reviewId = req.getParameter("reviewId"); //리뷰 id를 받아와 조회함
        reviewService.removeReview(Long.parseLong(reviewId));//특정 식당의 리뷰를 삭제
        return "admin/adminPage";
    }

}
