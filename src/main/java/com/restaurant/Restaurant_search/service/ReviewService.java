package com.restaurant.Restaurant_search.service;

import com.restaurant.Restaurant_search.entity.Review;
import com.restaurant.Restaurant_search.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RestaurantService restaurantService;

    public void writeReview(Review review, MultipartFile file, String userId)  throws IOException{


        Integer restaurantId = review.getRestaurantId();
        String photo_path = uploadFile(file, restaurantId); //파일 업로드 경로

        review.setPhoto_path(photo_path);
        review.setUserID(userId);

        restaurantService.avgRestaurantRating(review.getRating(), restaurantId); //식당 평점 계산

        reviewRepository.save(review);



    }

    public String uploadFile(MultipartFile file, Integer id) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\files"; //파일 경로
        String fileName = "review_"+ id +"_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile); //파일 경로에 저장

        return "\\img\\files\\"+fileName;

    }


    public List<Review> getReviewsByRestaurantId(int restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }


    public Page<Review> paging(Pageable pageable, int restaurantId) { //페이지를 쉽게 나눠줌
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 5; // 한페이지에 보여줄 글 개수

        // 한 페이지당 5개씩 글을 보여주고 정렬 기준은 restaurantId기준으로 내림차순
        Page<Review> postsPages = reviewRepository.findAllByRestaurantId(restaurantId, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "reviewID")));

        return postsPages;
    }

    public void removeReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }
}
