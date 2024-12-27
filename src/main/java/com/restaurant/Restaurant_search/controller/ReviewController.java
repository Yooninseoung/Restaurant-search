package com.restaurant.Restaurant_search.controller;

import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

// 식당의 사용자 리뷰를 관리하는 컨트롤러

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/writeForm") //리뷰 작성 화면 반환
    public String writeForm(HttpServletRequest req, Model model,
                            @SessionAttribute(name = "userId", required = false) String userId) throws IOException {
        if(userId==null){
            return "redirect:/user/login";
        }
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

    @GetMapping("/ReviewImg/{fileName}") //리뷰 사진을 반환
    public ResponseEntity<Resource> getReviewImage(@PathVariable("fileName") String fileName) {
        // 실제 파일 경로 설정
        File file = new File("C:/GangwonCookImg/ReviewImg/" + fileName);

        // 파일이 존재하는지 확인
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }


        // 파일을 리소스로 변환
        Resource resource = new FileSystemResource(file);

        // 이미지 파일을 클라이언트로 반환
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .body(resource);
    }
}
