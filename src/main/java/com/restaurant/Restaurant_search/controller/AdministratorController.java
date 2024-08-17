package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Board;
import com.restaurant.Restaurant_search.entity.Restaurant;
import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    BoardService boardService;

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
    public String manageBoard(@PageableDefault(page = 0, size = 10, sort = "boardId",
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {


        Page<Board> boardsPage = boardService.getAllBoards(pageable); // 서비스 호출
        int totalPages = boardsPage.getTotalPages();
        int currentPage = boardsPage.getNumber() + 1; // 현재 페이지 (0부터 시작)

        // 시작 페이지 계산: 현재 페이지를 기준으로 앞 4개 페이지를 보여줌
        int startPage = Math.max(1, currentPage - 4);

        // 종료 페이지 계산: 현재 페이지를 기준으로 뒤 5개 페이지를 보여줌
        int endPage = Math.min(totalPages, currentPage + 5);

        model.addAttribute("boards", boardsPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "admin/boardManagePage";
    }

    @GetMapping("/detail/{boardId}")
    public String showBoardDetailPage(@PathVariable("boardId") Integer boardId, Model model) {
        Optional<Board> optionalBoard = boardService.getBoardById(boardId);

        //명확한 NULL 처리를 위해 사용
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get(); // Optional에서 실제 Board 객체를 추출
            model.addAttribute("board", board);
            return "admin/boardDetailPage"; // "board/boardDetail.html"을 반환
        } else {
            return "redirect:/manage/board"; // 게시글 목록 페이지로 리다이렉트
        }
    }

    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Integer boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/manage/board";
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
